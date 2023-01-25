package com.samsung.sodam.jwt;

import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final CounselorRepository conselorRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = null;
        Client client = clientRepository.findByClientId(id);
        Counselor counselor = conselorRepository.findByCounselorId(id);
        System.out.println("UserDetailsServiceImpl - "+id);
        
        if(client == null && counselor == null) throw new UsernameNotFoundException("로그인 실패");

        if(client != null){
            member = new Member(client.getClientId(), client.getPassword(), Role.CLIENT);
        } else{
            member = new Member(counselor.getCounselorId(), counselor.getPassword(), Role.COUNSELOR);
        }
        return new UserDetailsImpl(member);
    }
}
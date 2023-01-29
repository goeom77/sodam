package com.samsung.sodam.jwt;

import com.samsung.sodam.db.entity.Client;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Member;
import com.samsung.sodam.db.repository.ClientRepository;
import com.samsung.sodam.db.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;
    private final CounselorRepository conselorRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Member member = null;
        Optional<Client> client = clientRepository.findById(id);
        Optional<Counselor> counselor = conselorRepository.findById(id);
        System.out.println("UserDetailsServiceImpl - "+id);
        
        if(client.isEmpty() && counselor.isEmpty()) throw new UsernameNotFoundException("로그인 실패");

        if(client.isEmpty()) member = counselor.orElse(null);
        else member = client.orElse(null);

        return new UserDetailsImpl(member);
    }
}
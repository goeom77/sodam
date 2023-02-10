package com.samsung.sodam.api.service;

import com.samsung.sodam.api.request.CertRequest;
import com.samsung.sodam.api.request.CounselorSignupRequest;
import com.samsung.sodam.api.request.EduRequest;
import com.samsung.sodam.api.response.CounselorDetailResponse;
import com.samsung.sodam.db.entity.Certificate;
import com.samsung.sodam.db.entity.Counselor;
import com.samsung.sodam.db.entity.Education;
import com.samsung.sodam.db.entity.ProfilePhoto;
import com.samsung.sodam.db.repository.*;
import com.samsung.sodam.db.repository.counselor.CounselorCustomRepository;
import com.samsung.sodam.db.repository.counselor.CounselorRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class CounselorProfileService {
    private final CounselorRepository conselorRepository;
    private final CounselorCustomRepository counselorCustomRepository;
    private final CertificateRepository certRepository;
    private final EducationRepository eduRepository;
    private final ProfilePhotoRepository profilePhotoRepository;
    private final FileGCSService fileService;

    public CounselorDetailResponse getCounselorDetailAll(String id){
        return counselorCustomRepository.getCounselorDetailAll(id);
    }

    public void uploadAssociateProfileTable(CounselorSignupRequest request) throws IOException {
        Counselor counselor = conselorRepository.getById(request.getId());

        if(request.getCertificates() != null && !request.getCertificates().isEmpty())
            uploadCertificate(request.getCertificates(), counselor);

        if(request.getEducations() != null && !request.getEducations().isEmpty())
            uploadEducation(request.getEducations(), counselor);

        // 경력 추가
        if(request.getCareers() != null){
            counselor.setCareer(request.getCareers());
            conselorRepository.save(counselor);
        }

    }

    public void deleteAssociateProfileTable(ArrayList<Long> eduDelete, ArrayList<Long> certDelete) {
//        List<ProfilePhoto> eduPhotoList =  eduRepository.selectPhotoByIdInQuery(eduDelete);
//        List<Long> eduIdList = new ArrayList<>();
//        for (ProfilePhoto photo : eduPhotoList){
//            eduIdList.add(photo.getId());
//        }
//
//        eduRepository.deleteAllByIdInQuery(eduDelete);
//        System.out.println(eduIdList);
//        profilePhotoRepository.deleteAllByIdInQuery(eduIdList);
        deleteEducation(eduDelete);
        deleteCertification(certDelete);
    }

    private void deleteEducation(ArrayList<Long> list){
        List<ProfilePhoto> photoList =  eduRepository.selectPhotoByIdInQuery(list);
        List<Long> eduIdList = new ArrayList<>();
        for (ProfilePhoto photo : photoList){
            eduIdList.add(photo.getId());
            // gcp에서 삭제 구현하기
        }

        eduRepository.deleteAllByIdInQuery(list);
        profilePhotoRepository.deleteAllByIdInQuery(eduIdList);
    }
    private void deleteCertification(ArrayList<Long> list){
        List<ProfilePhoto> photoList =  certRepository.selectPhotoByIdInQuery(list);
        List<Long> eduIdList = new ArrayList<>();
        for (ProfilePhoto photo : photoList){
            eduIdList.add(photo.getId());
        }
        certRepository.deleteAllByIdInQuery(list);
        profilePhotoRepository.deleteAllByIdInQuery(eduIdList);
    }

    private void uploadCertificate(ArrayList<CertRequest> list, Counselor counselor) throws IOException {
        for (CertRequest c : list){
            MultipartFile file = c.getFile();
            String url = fileService.fileUploadGCS(file, "cert");
            if(url == null) return;

            ProfilePhoto photo =
                    profilePhotoRepository.save(ProfilePhoto.builder()
                                    .url(url)
                                    .name(c.getFile().getOriginalFilename())
                    .build());

            certRepository.save(Certificate.builder()
                            .name(c.getName())
                            .agency(c.getAgency())
                            .serial_num(c.getSerial_num())
                            .counselor(counselor)
                            .photo(photo)
                    .build());
        }
    }

    private void uploadEducation(ArrayList<EduRequest> list, Counselor counselor) throws IOException {
        for (EduRequest o : list){
            MultipartFile file = o.getFile();
            String url = fileService.fileUploadGCS(file, "edu");
            if(url == null) return;

            ProfilePhoto photo =
                    profilePhotoRepository.save(ProfilePhoto.builder()
                            .url(url)
                            .name(o.getFile().getOriginalFilename())
                            .build());

            eduRepository.save(Education.builder()
                    .degree(o.getDegree())
                    .school(o.getSchool())
                    .major(o.getMajor())
                    .is_graduate((o.getIs_graduate()))
                    .counselor(counselor)
                    .photo(photo)
                    .build());
        }

    }



}

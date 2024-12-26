package com.example.demo.service;

import com.example.demo.DTO.ContactInfoDto;
import com.example.demo.DTO.mapper.ContactInfoMapper;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.ContactInfoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class ContactInfoService {
    private final ContactInfoRepository contactInfoRepository;
    private final ContactInfoMapper contactInfoMapper;

    public ContactInfoDto getContactInfo(Long id) {
        var info = contactInfoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Не существует контактной информации с таким id: " + id));
        return contactInfoMapper.toDto(info);
    }

    public ContactInfoDto updateContactInfo(Long id, ContactInfoDto contactDetails) {
        var contactInfo = contactInfoRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Не существует контактной информации с таким id: " + id));

        contactInfoMapper.partialUpdate(contactDetails, contactInfo);

        return contactInfoMapper.toDto(contactInfoRepository.save(contactInfo));
    }
}

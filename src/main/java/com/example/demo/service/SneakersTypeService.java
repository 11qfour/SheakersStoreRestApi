package com.example.demo.service;

import com.example.demo.DTO.SneakersTypeDtoRequest;
import com.example.demo.DTO.SneakersTypeDtoResponse;
import com.example.demo.DTO.mapper.SneakersTypeMapper;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Sneakers;
import com.example.demo.model.SneakersType;
import com.example.demo.repository.SneakersRepository;
import com.example.demo.repository.SneakersTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service  //принимает как сервис
// Сервисы обычно содержат бизнес-логику приложения и могут использоваться для обработки запросов и взаимодействия с репозиториями
@Transactional
//Если какое-либо из выполняющихся подзадач завершится с ошибкой, все изменения, сделанные за время жизни этой транзакции, будут отменены
@RequiredArgsConstructor
public class SneakersTypeService {
    private final SneakersTypeRepository sneakersTypeRepository;
    private final SneakersTypeMapper sneakersTypeMapper;
    private final SneakersRepository sneakersRepository;

    public void deleteSneakersType(Long id){
        if (!sneakersTypeRepository.existsById(id)){
            throw new ResourceNotFoundException("Катагория не найдена с id: " + id);
        }
        sneakersTypeRepository.deleteById(id);
    }

    public List<SneakersTypeDtoResponse> getAllTypeSneackers(){
        return sneakersTypeRepository.findAll().stream().map(sneakersTypeMapper::toDto).toList();
    }

    public SneakersTypeDtoResponse getSneakersTypeById(Long id){
        var sneakersType = sneakersTypeRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Кроссовки не найдены с id: " + id));
        return sneakersTypeMapper.toDto(sneakersType);
    }

    // Метод для создания типа кроссовок
    public SneakersTypeDtoResponse create(SneakersTypeDtoRequest sneakersTypeDto) {
        // Преобразование DTO в Entity
        SneakersType sneakersType = sneakersTypeMapper.toEntity(sneakersTypeDto);

        // Устанавливаем кроссовки по их идентификаторам
        if (sneakersTypeDto.getSneakers() != null || sneakersTypeDto.getSneakers().size()!=0) {
            Set<Sneakers> sneakersSet = sneakersTypeMapper.map(sneakersTypeDto.getSneakers()); // Преобразуем ID в объекты
            sneakersType.setSneakers(sneakersSet);
        }

        // Сохранение типа кроссовок и возврат результата
        var savedTypeSneakers = sneakersTypeRepository.save(sneakersType);
        return sneakersTypeMapper.toDto(savedTypeSneakers);
    }

    public SneakersTypeDtoResponse updateSneakersType(Long id, SneakersTypeDtoRequest sneakersTypeDtoRequest) {
        var existingType = sneakersTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Тип кроссовок не найден с id: " + id));

        sneakersTypeMapper.partialUpdate(sneakersTypeDtoRequest, existingType);

        // Устанавливаем кроссовки по их идентификаторам
        if (sneakersTypeDtoRequest.getSneakers() != null) {
            Set<Sneakers> sneakersSet = sneakersTypeMapper.map(sneakersTypeDtoRequest.getSneakers());
            existingType.setSneakers(sneakersSet);
        }

        return sneakersTypeMapper.toDto(sneakersTypeRepository.save(existingType));
    }
}

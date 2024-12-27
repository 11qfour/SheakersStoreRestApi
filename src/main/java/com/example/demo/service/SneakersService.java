package com.example.demo.service;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SneakersDtoResponse;
import com.example.demo.DTO.mapper.SneakersMapper;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Sneakers;
import com.example.demo.model.SneakersType;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SneakersRepository;
import com.example.demo.repository.SneakersTypeRepository;
import com.example.demo.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service  //принимает как сервис
// Сервисы обычно содержат бизнес-логику приложения и могут использоваться для обработки запросов и взаимодействия с репозиториями
@Transactional //Если какое-либо из выполняющихся подзадач завершится с ошибкой, все изменения, сделанные за время жизни этой транзакции, будут отменены
@RequiredArgsConstructor //генерирует конструктор, который принимает все поля класса, помеченные как final
public class SneakersService {
    private final SneakersRepository sneakersRepository;
    private final SneakersMapper sneakersMapper;
    private final SneakersTypeRepository sneakersTypeRepository;
    private final SupplierRepository supplierRepository;

    public SneakersDtoResponse create(SneakersDtoRequest sneakersDto){   //метод создания кроссовок
        Sneakers sneakers = sneakersMapper.toEntity(sneakersDto);
        sneakers.setSneakerModel(sneakersDto.getSneakerModel());
        //установка категории
        SneakersType sneakersType = sneakersTypeRepository.findById(sneakersDto.getSneakersTypeId()).orElseThrow(() ->
                new ResourceNotFoundException("Тип кроссовок не найден с id: " + sneakersDto.getSneakersTypeId()));
        sneakers.setSneakersType(sneakersType);

        //установка поставщиков
        /*Set<Supplier> suppliers = sneakersDto.getSupplierIds().stream()
                .map(supplierId -> supplierRepository.findById(supplierId).orElseThrow(() ->
                        new ResourceNotFoundException("Поставщик не найден с id: " + supplierId)))
                .collect(Collectors.toSet());
        sneakers.setSuppliers(suppliers);*/

        var savedProduct = sneakersRepository.save(sneakers);
        return sneakersMapper.toDto(savedProduct);
    }

    public SneakersDtoResponse getSneakersById(Long id){
        var sneakers = sneakersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Кроссовки не найдены с id: " + id));
        return sneakersMapper.toDto(sneakers);
    }

    public List<SneakersDtoResponse> getAllSneakers(){
        return sneakersRepository.findAll().stream().map(sneakersMapper::toDto).toList();
    }
    public SneakersDtoResponse updateSneakers(Long id, SneakersDtoRequest sneakersDetails){
        var product = sneakersRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Кроссовки не найдены с id: " + id));

        sneakersMapper.partialUpdate(sneakersDetails, product);

        if (sneakersDetails.getSneakersTypeId() != null) {
            SneakersType sneakersType = sneakersTypeRepository.findById(sneakersDetails.getSneakersTypeId()).orElseThrow(() ->
                    new ResourceNotFoundException("Тип кроссовок не найден с id: " + sneakersDetails.getSneakersTypeId()));
            product.setSneakersType(sneakersType);
        }

        if (sneakersDetails.getSupplierIds() != null) {
            Set<Supplier> suppliers = sneakersDetails.getSupplierIds().stream()
                    .map(supplierId -> supplierRepository.findById(supplierId).orElseThrow(() ->
                            new ResourceNotFoundException("Поставщик не найден с id: " + supplierId)))
                    .collect(Collectors.toSet());
            product.setSuppliers(suppliers);
        }

        return sneakersMapper.toDto(sneakersRepository.save(product));
    }

    public void deleteSneakers(Long id){
        if (!sneakersRepository.existsById(id)) {
            throw new ResourceNotFoundException("Кроссовки не найдены с id: " + id);
        }
        sneakersRepository.deleteById(id);
    }
}

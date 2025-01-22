package com.example.demo.service;

import com.example.demo.DTO.SneakersDtoRequest;
import com.example.demo.DTO.SupplierDtoRequest;
import com.example.demo.DTO.SupplierDtoResponse;
import com.example.demo.DTO.mapper.SupplierMapper;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Sneakers;
import com.example.demo.model.SneakersType;
import com.example.demo.model.Supplier;
import com.example.demo.repository.SneakersRepository;
import com.example.demo.repository.SupplierRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service  //принимает как сервис
// Сервисы обычно содержат бизнес-логику приложения и могут использоваться для обработки запросов и взаимодействия с репозиториями
@Transactional
//Если какое-либо из выполняющихся подзадач завершится с ошибкой, все изменения, сделанные за время жизни этой транзакции, будут отменены
@RequiredArgsConstructor
public class SupplierService {
    private final SneakersRepository sneakersRepository;
    private final SupplierMapper supplierMapper;
    private final SupplierRepository supplierRepository;

    public void deleteSupplier(Long id){
        if (!supplierRepository.existsById(id)) {
            throw new ResourceNotFoundException("Поставщик не найден с id: " + id);
        }
        supplierRepository.deleteById(id);
    }

    public SupplierDtoResponse getSupplierById(Long id){
        var sneakers = supplierRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Поставщик не найдены с id: " + id));
        return supplierMapper.toDto(sneakers);
    }

    public List<SupplierDtoResponse> getAllSupplier(){
        return supplierRepository.findAll().stream().map(supplierMapper::toDto).toList();
    }

    public SupplierDtoResponse create(SupplierDtoRequest supplierDtoRequest){   //метод создания кроссовок
        Supplier supplier = supplierMapper.toEntity(supplierDtoRequest);

        Set<Sneakers> sneakers = new HashSet<>();
        if (supplierDtoRequest.getSneakers() != null) {
            sneakers = supplierDtoRequest.getSneakers().stream()
                    .map(sneakersId -> {
                        Long id = sneakersId.getId(); // Предполагается, что sneakersId - это объект класса Sneakers
                        return sneakersRepository.findById(id)
                                .orElseThrow(() -> new ResourceNotFoundException("Кроссовки не найдены с id: " + id));
                    })
                    .collect(Collectors.toSet());
        } else {
            throw new IllegalArgumentException("Список кроссовок не должен быть null!");
        }


        Supplier savedSupplier = supplierRepository.save(supplier);
        return supplierMapper.toDto(savedSupplier);
    }

    public SupplierDtoResponse updateSneakers(Long id, SupplierDtoRequest supplierDtoRequest){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Поставщик не найден с id: " + id));

        supplierMapper.partialUpdate(supplierDtoRequest, supplier);

        if (supplierDtoRequest.getSneakers() != null && !supplierDtoRequest.getSneakers().isEmpty()) {
            Set<Sneakers> sneakers = supplierDtoRequest.getSneakers().stream()
                    .map(sneakersId -> sneakersRepository.findById(sneakersId.getId()).orElseThrow(() ->
                            new ResourceNotFoundException("Кроссовки не найдены с id: " + sneakersId)))
                    .collect(Collectors.toSet());
            supplier.setSneakers(sneakers);
        }

        return supplierMapper.toDto(supplierRepository.save(supplier));
    }
}

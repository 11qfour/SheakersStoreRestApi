package com.example.demo.service;

import com.example.demo.model.Sneakers;
import com.example.demo.repository.SneakersRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service  //принимает как сервис
// Сервисы обычно содержат бизнес-логику приложения и могут использоваться для обработки запросов и взаимодействия с репозиториями
@RequiredArgsConstructor //генерирует конструктор, который принимает все поля класса, помеченные как final
public class SneakersService {
    private final SneakersRepository sneakersRepository;

    public Sneakers create(Sneakers sneakers){   //метод создания кроссовок
         return sneakersRepository.save(sneakers);
    }

    public Sneakers findById(Long id){
        return sneakersRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Кроссовок с таким id: %d не существует".formatted(id))
        );
    }

    public List<Sneakers> findAll(){
        return sneakersRepository.findAll();
    }
    public Sneakers update(Long id, Sneakers sneakersDetails){
        Sneakers sneakers= sneakersRepository.findById(id).orElseThrow(
                ()->new RuntimeException("Кроссовок с таким id: %d не существует".formatted(id))
        );
        sneakers.setSneakerModel(sneakersDetails.getSneakerModel());
        sneakers.setType(sneakersDetails.getType());
        return sneakersRepository.save(sneakers);
    }

    public void delete(Long id){
        sneakersRepository.deleteById(id);
    }
}

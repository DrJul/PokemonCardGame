package com.pokemontcg.service;

import com.pokemontcg.dto.RegisterRequestDto;
import com.pokemontcg.entity.TrenerEntity;
import com.pokemontcg.entity.UserEntity;
import com.pokemontcg.exception.RegisterServiceException;
import com.pokemontcg.mapper.UserMapper;
import com.pokemontcg.repository.TrenerRepository;
import com.pokemontcg.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegisterService {
    private UserRepository userRepository;
    private TrenerRepository trenerRepository;
    private UserMapper userMapper;

    public void registerUser(RegisterRequestDto userRequestDto){
        if (!doPasswordsMatch(userRequestDto)) {
            throw new RegisterServiceException("Given passwords should be same");
        }
        boolean ifEmailExist = userRepository.existsByEmail(userRequestDto.getEmail());
        if (ifEmailExist) {
            throw new RegisterServiceException("User with given email exist");
        }
        UserEntity userToSave = userMapper.mapToUserEntity(userRequestDto);
        TrenerEntity trenerToSave = userToSave.getTrener();
        trenerRepository.save(trenerToSave);
        userRepository.save(userToSave);
    }

    private boolean doPasswordsMatch(RegisterRequestDto userRequestDto) {
        return userRequestDto.getPassword().equals(userRequestDto.getRepeatedPassword());
    }
}

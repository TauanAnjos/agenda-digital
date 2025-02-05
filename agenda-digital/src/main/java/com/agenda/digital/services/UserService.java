package com.agenda.digital.services;
import com.agenda.digital.models.UserModel;
import com.agenda.digital.repositories.UserRepository;
import com.agenda.digital.rest.dtos.UserDtoResponse;
import com.agenda.digital.rest.dtos.UserDtoResquest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Transactional
    public void cadastrarUsuario(UserModel userModel){
        if (userRepository.existsByEmail(userModel.getEmail())){
            throw new RuntimeException("Email já cadastrado.");
        }
        try{
            userModel.setSenha(new BCryptPasswordEncoder().encode(userModel.getSenha()));
            userRepository.save(userModel);
        }catch (RuntimeException e){
            throw new RuntimeException("Erro ao cadastrar usuário.");
        }
    }
    @Transactional(readOnly = true)
    public UserDtoResponse buscarUsuarioPorId(Long userId){
        try {
            UserDtoResponse userExistente = userRepository.findById(userId).get().toDtoResponse();
            return userExistente;
        }catch (RuntimeException e){
           throw new RuntimeException("Usuário não encontrado.");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }
}

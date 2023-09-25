package com.springboot.awss3app.datasource.h2;


import com.springboot.awss3app.model.User;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

//@Component
@AllArgsConstructor
public class H2DataLoader {

//    final private UsersH2Repository h2Repository;

//    @PostConstruct
//    void loadDataToH2Database() {
//        this.h2Repository.saveAll(
//                List.of(
//                        new User(UUID.randomUUID(), "alex", null),
//                        new User(UUID.randomUUID(), "antonio", null)
//                )
//        );
//    }
}

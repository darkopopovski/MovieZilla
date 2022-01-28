package com.example.demo.repository;

import com.example.demo.model.Client.Client;
import com.example.demo.model.Client.ClientCompositeKey;
import com.example.demo.model.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, ClientCompositeKey> {

    @Query(value="select * from Client where user_id=?",nativeQuery = true)
    Optional<Client> findClient(Integer user_id);

    @Query(value="select * from Client ", nativeQuery = true)
    public Optional<Client> findAllClients();

    @Query(value=" select c.user_id,sum(mp.projection_price) as vkupna_potroshuvacka,u.user_name,u.user_surname from users as u\n" +
            "     join client as c on c.user_id = u.user_id\n" +
            "     join reservation as r on r.user_id = c.user_id\n" +
            "     and r.reservation_date between now() - interval '1 year' and now()\n" +
            "     join movieprojection as mp on mp.projection_id = r.projection_id\n" +
            "     where mp.projection_id = ?\n" +
            "     group by c.user_id,u.user_name,u.user_surname\n" +
            "     order by vkupna_potroshuvacka desc\n" +
            "     limit 1;;",nativeQuery = true)
    public List<Client> findBestClient(Integer projection_id);

}
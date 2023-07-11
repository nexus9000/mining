package edu.itstep.blockchain.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_names")
public class User {
 @Id
 @GeneratedValue(strategy = GenerationType.AUTO)
 Long id;
 
 String name;
 
 String password;
}

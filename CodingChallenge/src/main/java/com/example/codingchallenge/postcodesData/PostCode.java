package com.example.codingchallenge.postcodesData;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class PostCode {

    @Id
    public UUID id;
    public String federalState;
    public String land;
    public String city;
    public String postalCode;
}

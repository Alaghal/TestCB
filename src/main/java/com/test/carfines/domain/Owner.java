package com.test.carfines.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "OWNERS")
public class Owner {
    @Id
    @Column(name = "OWNER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<LicensePlateNumber> licensePlateNumbers;

    @Column(name = "OWNER_NAME")
    @Pattern(regexp = "^[A-Za-z\\s]{0,45}$|^[А-Яа-я\\s]{0,45}$",message = "Имя не должно содержать цифр и спец символов")
    private String nameOwner;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder( "Owner = [ id = " + id + ", nameOwner = " + nameOwner + ", licensePlateNumbers = {" );
        if (licensePlateNumbers==null || licensePlateNumbers.isEmpty()) {
            stringBuilder.append( " }]" );
        } else {
            for (var licensePlateNumber : licensePlateNumbers) {
                stringBuilder.append( " " + licensePlateNumber.toString() + "," );
            }

            int lastComma = stringBuilder.length() - 1;
            stringBuilder.deleteCharAt( lastComma );
            stringBuilder.append( " }]" );
        }
        return stringBuilder.toString();
    }

}

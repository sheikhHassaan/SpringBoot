package com.example.springproject.entity;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@GroupSequence({Department.class, ExcludeIdValidation.class})

public class Department {

//    @Id @GeneratedValue(generator="system-uuid")
//    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Id @UuidGenerator
    @SerializedName("department_id")
    private String departmentId;

    @NotNull
    @SerializedName("department_name")
    private String departmentName;

    @SerializedName("department_code")
    private String departmentCode;

    @SerializedName("department_address")
    private String departmentAddress;
}

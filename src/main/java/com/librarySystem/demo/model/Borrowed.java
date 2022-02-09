package com.librarySystem.demo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Schema(description = "Borrowed Information")
@Data
@EntityListeners(AuditingEntityListener.class)
public class Borrowed implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema()
    @NotBlank
    private String bookId;

    @NotNull
    private Long readerId;

    @LastModifiedDate
    private Date borrowedDate;

    private boolean expired=false;
}

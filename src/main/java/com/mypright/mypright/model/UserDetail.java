package com.mypright.mypright.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private String id;

  private String detailName;

  private String reason;

  private String detailValue;

  @Override
  public String toString() {
    return "{" +
        "detailName:'" + detailName + '\'' +
        ", reason:'" + reason + '\'' +
        ", detailValue:'" + detailValue + '\'' +
        '}';
  }
}

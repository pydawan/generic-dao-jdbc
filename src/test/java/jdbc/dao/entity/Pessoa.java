package jdbc.dao.entity;

import java.util.Date;

import jdbc.dao.Transient;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
public class Pessoa {
   
   private String nome;
   private String sobrenome;
   
   @Transient
   private Date dataNascimento;
   
   public String getNome() {
      return nome;
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public String getSobrenome() {
      return sobrenome;
   }
   
   public void setSobrenome(String sobrenome) {
      this.sobrenome = sobrenome;
   }
   
   public Date getDataNascimento() {
      return dataNascimento;
   }
   
   public void setDataNascimento(Date dataNascimento) {
      this.dataNascimento = dataNascimento;
   }
   
}

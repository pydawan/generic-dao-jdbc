package jdbc.dao;

import java.util.Date;

/**
 * @author thiago-amm
 * @version v1.0.0 04/11/2017
 * @since v1.0.0
 */
@Table(name = "pessoa")
public class Pessoa {
   
   private String nome;
   private String sobrenome;
   private Date dataNascimento;
   
   public Pessoa() {
      
   }
   
   public static Pessoa create() {
      return new Pessoa();
   }
   
   public String getNome() {
      return nome;
   }
   
   public String nome() {
      return getNome();
   }
   
   public void setNome(String nome) {
      this.nome = nome;
   }
   
   public Pessoa nome(String nome) {
      setNome(nome);
      return this;
   }
   
   public String getSobrenome() {
      return sobrenome;
   }
   
   public String sobrenome() {
      return getSobrenome();
   }
   
   public void setSobrenome(String sobrenome) {
      this.sobrenome = sobrenome;
   }
   
   public Pessoa sobrenome(String sobrenome) {
      setSobrenome(sobrenome);
      return this;
   }
   
   public Date getDataNascimento() {
      return dataNascimento;
   }
   
   public Date dataNascimento() {
      return getDataNascimento();
   }
   
   public void setDataNascimento(Date dataNascimento) {
      this.dataNascimento = dataNascimento;
   }
   
   public Pessoa dataNascimento(Date dataNascimento) {
      setDataNascimento(dataNascimento);
      return this;
   }
   
}

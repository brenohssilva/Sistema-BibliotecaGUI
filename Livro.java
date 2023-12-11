package Projeto;

//Importa a classe Date do pacote java.util para lidar com datas.
import java.util.Date;

//Declaração da classe Livro.
public class Livro {
 // Declaração de variáveis membros da classe.
 String nome;              // Nome do livro.
 String autor;             // Autor do livro.
 String genero;            // Gênero do livro.
 int codigo;               // Código do livro.
 boolean disponivel;       // Indica se o livro está disponível para aluguel.
 String clienteAluguel;    // Nome do cliente que alugou o livro.
 Date dataDevolucao;       // Data prevista para a devolução do livro.
 Date dataAluguel;         // Data em que o livro foi alugado.

 // Construtor da classe Livro.
 Livro(String nome, String autor, String genero, int codigo) {
     // Inicializa os membros da classe com os valores passados como parâmetros.
     this.nome = nome;
     this.autor = autor;
     this.genero = genero;
     this.codigo = codigo;
     this.disponivel = true;           // Inicialmente, o livro está disponível.
     this.clienteAluguel = null;       // Inicialmente, nenhum cliente alugou o livro.
     this.dataDevolucao = null;        // Inicialmente, a data de devolução não está definida.
     this.dataAluguel = null;          // Inicialmente, a data de aluguel não está definida.
 }

 // Método para definir a data de aluguel do livro.
 public void setDataAluguel(Date dataAluguel) {
     this.dataAluguel = dataAluguel;
 }

 // Método para definir a data de devolução do livro.
 public void setDataDevolucao(Date dataDevolucao) {
     this.dataDevolucao = dataDevolucao;
 }

 // Método para obter a data de aluguel do livro.
 public Date getDataAluguel() {
     return this.dataAluguel;
 }

 // Método para obter a data de devolução do livro.
 public Date getDataDevolucao() {
     return this.dataDevolucao;
 }

 // Método para obter o código do livro.
 public int getCodigo() {
     return this.codigo;
 }

 // Método para verificar se o livro está disponível.
 public boolean isDisponivel() {
     return this.disponivel;
 }

 // Método para obter o nome do livro.
 public String getNome() {
     return this.nome;
 }
}

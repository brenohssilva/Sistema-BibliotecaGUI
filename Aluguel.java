package Projeto;

//Importa a classe Date do pacote java.util para lidar com datas.
import java.util.Date;

//Declaração da classe Aluguel.
public class Aluguel {
 // Declaração de variáveis membros da classe.
 int codigoLivro;          // Código do livro associado ao aluguel.
 Date dataAluguel;         // Data em que o livro foi alugado.
 Date dataDevolucao;       // Data prevista para a devolução do livro.
 String nomeLivro;         // Nome do livro associado ao aluguel.
 int matriculaCliente;     // Matrícula do cliente que realizou o aluguel.

 // Construtor da classe Aluguel.
 Aluguel(int codigoLivro, Date dataAluguel, Date dataDevolucao, String nomeLivro, int matriculaCliente) {
     // Inicializa os membros da classe com os valores passados como parâmetros.
     this.codigoLivro = codigoLivro;
     this.dataAluguel = dataAluguel;
     this.dataDevolucao = dataDevolucao;
     this.nomeLivro = nomeLivro;
     this.matriculaCliente = matriculaCliente;
 }

 // Método para obter a data de aluguel.
 public Date getDataAluguel() {
     return this.dataAluguel;
 }

 // Método para obter a data prevista para a devolução.
 public Date getDataDevolucao() {
     return this.dataDevolucao;
 }
}

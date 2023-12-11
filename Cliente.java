package Projeto;

//Importa as classes ArrayList e List do pacote java.util para lidar com listas.
import java.util.ArrayList;
import java.util.List;

//Declaração da classe Cliente.
public class Cliente {
 // Declaração de variáveis membros da classe.
 String nome;              // Nome do cliente.
 int matricula;            // Matrícula do cliente.
 List<Aluguel> alugueis;   // Lista de aluguéis associados ao cliente.

 // Construtor da classe Cliente.
 Cliente(String nome, int matricula) {
     // Inicializa os membros da classe com os valores passados como parâmetros.
     this.nome = nome;
     this.matricula = matricula;
     // Inicializa a lista de aluguéis como uma instância de ArrayList.
     this.alugueis = new ArrayList<>();
 }

 // Método para adicionar um aluguel à lista de aluguéis do cliente.
 void adicionarAluguel(Aluguel aluguel) {
     alugueis.add(aluguel);
 }

 // Método para obter informações sobre os aluguéis do cliente.
 String obterInformacoesAlugueis() {
     // Cria um StringBuilder para compor as informações dos aluguéis.
     StringBuilder info = new StringBuilder();
     // Itera sobre a lista de aluguéis.
     for (Aluguel aluguel : alugueis) {
         // Adiciona as informações do aluguel ao StringBuilder.
         info.append("Livro: ").append(aluguel.nomeLivro)
                 .append(", Data de Aluguel: ").append(aluguel.dataAluguel)
                 .append(", Data de Devolução: ").append(aluguel.dataDevolucao)
                 .append("\n");
     }
     // Converte o StringBuilder para uma String e a retorna.
     return info.toString();
 }
}

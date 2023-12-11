package Projeto;

//Importa classes necessarias do pacote javax.swing e java.awt
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

//Classe principal para a interface grafica da biblioteca

public class BibliotecaGUI extends JFrame {
	 //Listas para armazenar livros, clientes e alugueis.
    private List<Livro> livros = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();
    private List<Aluguel> alugueis = new ArrayList<>(); 

//Area de texto para exibit saida.
    private JTextArea outputArea;

    //Construtor da classe BibliotecaGUI
    BibliotecaGUI() {
        super("Biblioteca");

        //Configuração da interface grafica
        outputArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2));
//Botão para cadastrar livro.
        JButton cadastrarLivroButton = new JButton("Cadastrar Livro");
        cadastrarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarLivro();
            }
        });
        panel.add(cadastrarLivroButton);
//Botão para cadastrar cliente
        JButton cadastrarClienteButton = new JButton("Cadastrar Cliente");
        cadastrarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarCliente();
            }
        });
        panel.add(cadastrarClienteButton);
//Botão para buscar livro.
        JButton buscarLivroButton = new JButton("Buscar Livro");
        buscarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarLivro();
            }
        });
        panel.add(buscarLivroButton);
//Botão para buscar cliente.
        JButton buscarClienteButton = new JButton("Buscar Cliente");
        buscarClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCliente();
            }
        });
        panel.add(buscarClienteButton);
//Botão para a alugar livro.
        JButton alugarLivroButton = new JButton("Alugar Livro");
        alugarLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alugarLivro();
            }
        });
        panel.add(alugarLivroButton);
//Botão para devolver Livro.
        JButton devolverLivroButton = new JButton("Devolver Livro");
        devolverLivroButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                devolverLivro();
            }
        });
        panel.add(devolverLivroButton);
//Botão para listar Livros de forma Ordenadas.
        JButton listarLivrosOrdenadosButton = new JButton("Listar Livros (Ord. Alfabética)");
        listarLivrosOrdenadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarLivrosOrdenados();
            }
        });
        panel.add(listarLivrosOrdenadosButton);
//Botão para listar clientes Ordenados.
        JButton listarClientesOrdenadosButton = new JButton("Listar Clientes (Ord. Alfabética)");
        listarClientesOrdenadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarClientesOrdenados();
            }
        });
//Botão para remover cliente
        JButton removerClienteButton = new JButton("Remover Cliente");
        removerClienteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removerCliente();
            }
        });
        
        



panel.add(removerClienteButton);


       
        panel.add(listarClientesOrdenadosButton);

        add(panel, BorderLayout.SOUTH);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    
    // Métodos relacionados às operações da biblioteca (cadastrar, buscar, alugar, etc.) podem ser adicionados aqui.
    
    private void removerCliente() {
    int matriculaRemover = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula do cliente a ser removido:"));

    // Encontrar o cliente pelo número de matrícula
    Cliente clienteRemover = null;
    for (Cliente cliente : clientes) {
        if (cliente.matricula == matriculaRemover) {
            clienteRemover = cliente;
            break;
        }
    }

    if (clienteRemover != null) {
        // Remover o cliente da lista
        clientes.remove(clienteRemover);

        // Remover os aluguéis associados ao cliente
        List<Aluguel> alugueisRemover = new ArrayList<>();
        for (Aluguel aluguel : alugueis) {
            if (aluguel.matriculaCliente == matriculaRemover) {
                alugueisRemover.add(aluguel);
            }
        }
        alugueis.removeAll(alugueisRemover);

        JOptionPane.showMessageDialog(null, "Cliente removido com sucesso!");
    } else {
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    }

    atualizarOutput();
}

//Metodo para cadastrar um novo livro
    private void cadastrarLivro() {
       //Solicita informações do livro ao usuario
    	String nome = JOptionPane.showInputDialog("Nome do livro:");
        String autor = JOptionPane.showInputDialog("Autor do livro:");
        String genero = JOptionPane.showInputDialog("Gênero do livro:");
        int codigo = Integer.parseInt(JOptionPane.showInputDialog("Código do livro:"));
//Criar um novo livro com as informções fornecidas e adiciona-la a lista de livros
        Livro livro = new Livro(nome, autor, genero, codigo);
        livros.add(livro);
        atualizarOutput(); //atualiza a area de saida da interface
    }

    //Metodo para cadastrar novo cliente
     private void cadastrarCliente() {
        //Solicitar informações do cliente ao usuario
    	 String nome = JOptionPane.showInputDialog("Nome do cliente:");
        int matricula = Integer.parseInt(JOptionPane.showInputDialog("Matrícula do cliente:"));
//Criar um novo cliente com as informções fornecidas e adiciona-lo a lista de clientes
        Cliente cliente = new Cliente(nome, matricula);
        clientes.add(cliente);
        atualizarOutput();
    }

     //Metodo para buscar um livro, permitindo escolher entre busca por nome ou codigo
    private void buscarLivro() {
       //Solicita ao usuario o tipo de busca desejado
    	String opcoesBusca[] = {"Por Nome", "Por Código"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha o tipo de busca:", "Buscar Livro",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesBusca, opcoesBusca[0]);
//Chamar metodos especificos de busca com base na escolha do usuario
        if (escolha == 0) {
            buscarLivroPorNome();
        } else if (escolha == 1) {
            buscarLivroPorCodigo();
        }
    }
//Metodo para buscar um cliente, permitindo escolher entre busca por nome ou matricula 
   private void buscarCliente() {
       //Solicitar ao usuario o tipo de busca desejado 
	   String opcoesBusca[] = {"Por Nome", "Por Matrícula"};
        int escolha = JOptionPane.showOptionDialog(null, "Escolha o tipo de busca:", "Buscar Cliente",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcoesBusca, opcoesBusca[0]);
//Chamar metodos especificos de busca com base na escolha do usuario
        if (escolha == 0) {
            buscarClientePorNome();
        } else if (escolha == 1) {
            buscarClientePorMatricula();
        }
    }
   
   //Metodo para alugar um livro
 private void alugarLivro() {
   // Solicitar a matrícula do cliente que deseja alugar
	 int matriculaCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula do cliente:"));

    // Verificar se o cliente existe
    Cliente clienteEncontrado = null;
    for (Cliente cliente : clientes) { // Se o cliente existir, continuar com o processo de aluguel.
        if (cliente.matricula == matriculaCliente) {
            clienteEncontrado = cliente;
            break;
        }
    }

    if (clienteEncontrado != null) {
        // Solicitar o gênero para filtrar a lista de livros
        String generoEscolhido = JOptionPane.showInputDialog("Digite o gênero desejado:");

        // Listar os livros disponíveis do gênero escolhido
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.disponivel && livro.genero.equalsIgnoreCase(generoEscolhido)) {
                livrosDisponiveis.add(livro);
            }
        }

        if (!livrosDisponiveis.isEmpty()) {
            // Mostrar os livros disponíveis do gênero escolhido
            StringBuilder livrosStr = new StringBuilder("Livros disponíveis no gênero " + generoEscolhido + ":\n");
            for (Livro livro : livrosDisponiveis) {
                livrosStr.append("Código: ").append(livro.codigo)
                        .append(", Nome: ").append(livro.nome)
                        .append(", Autor: ").append(livro.autor)
                        .append(", Status: ").append(livro.disponivel ? "Disponível" : "Indisponível");

                if (!livro.disponivel) {
                    livrosStr.append(", Cliente: ").append(livro.clienteAluguel)
                            .append(", Aluguel: ").append(new SimpleDateFormat("dd/MM/yyyy").format(livro.getDataAluguel()))
                            .append(", Devolução: ").append(new SimpleDateFormat("dd/MM/yyyy").format(livro.getDataDevolucao()));
                }

                livrosStr.append("\n");
            }

            // Adicionar a solicitação do código do livro na mesma tela
            livrosStr.append("\nDigite o código do livro que você deseja alugar:");

            // Solicitar o código do livro
            int codigoLivro;
            try {
                codigoLivro = Integer.parseInt(JOptionPane.showInputDialog(livrosStr.toString()));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Código do livro inválido!");
                return;
            }

            // Encontrar o livro escolhido
            Livro livroSelecionado = null;
            for (Livro livro : livrosDisponiveis) {
                if (livro.codigo == codigoLivro) {
                    livroSelecionado = livro;
                    break;
                }
            }

           if (livroSelecionado != null) {
        // Solicitar as datas de aluguel e devolução
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataAluguel = null;
        Date dataDevolucao = null;

        try {
            String strDataAluguel = JOptionPane.showInputDialog("Digite a data de aluguel (dd/MM/yyyy):");
            String strDataDevolucao = JOptionPane.showInputDialog("Digite a data de devolução (dd/MM/yyyy):");

            // Garantir que as datas sejam inicializadas corretamente
            dataAluguel = dateFormat.parse(strDataAluguel);
            dataDevolucao = dateFormat.parse(strDataDevolucao);

            // Definir as datas no livro
            livroSelecionado.setDataAluguel(dataAluguel);
            livroSelecionado.setDataDevolucao(dataDevolucao);

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Formato de data inválido!");
            return;
        }

                // Criar o aluguel e associar ao cliente
                Aluguel aluguel = new Aluguel(
                        livroSelecionado.codigo,
                        dataAluguel,
                        dataDevolucao,
                        livroSelecionado.nome,
                        matriculaCliente
                );
                clienteEncontrado.adicionarAluguel(aluguel);

                // Atualizar o livro com informações de aluguel
                livroSelecionado.disponivel = false;
                livroSelecionado.clienteAluguel = clienteEncontrado.nome;
                livroSelecionado.dataDevolucao = dataDevolucao;

                // Adicionar o aluguel à lista geral de aluguéis
                alugueis.add(aluguel);

                JOptionPane.showMessageDialog(null, "Livro alugado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Livro não encontrado.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não há livros disponíveis no gênero escolhido.");
        }
    } else {
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    }

    atualizarOutput();
}

    private void devolverLivro() {
        int codigoLivro = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro que você deseja devolver:"));

        // Encontrar o aluguel correspondente ao livro
        Aluguel aluguelEncontrado = null;
        for (Aluguel aluguel : alugueis) {
            if (aluguel.codigoLivro == codigoLivro) {
                aluguelEncontrado = aluguel;
                break;
            }
        }

        if (aluguelEncontrado != null) {
            // Marcar o livro como disponível novamente
            for (Livro livro : livros) {
                if (livro.codigo == aluguelEncontrado.codigoLivro) {
                    livro.disponivel = true;
                    break;
                }
            }

            // Remover o aluguel da lista de aluguéis
            alugueis.remove(aluguelEncontrado);

            JOptionPane.showMessageDialog(null, "Livro devolvido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Aluguel não encontrado para o livro informado.");
        }

        atualizarOutput();
    }
    
   
    private void buscarLivroPorNome() {
    String nomeBusca = JOptionPane.showInputDialog("Digite o nome do livro:");
    for (Livro livro : livros) {
        if (livro.nome.equalsIgnoreCase(nomeBusca)) {
            Aluguel aluguel = obterAluguelPorCodigoLivro(livro.codigo);
            mostrarLivroInfo(livro, aluguel);
            return;
        }
    }
    JOptionPane.showMessageDialog(null, "Livro não encontrado.");
}
 // Método para concatenar informações de um livro em um StringBuilder.
   private void appendLivroInfo(StringBuilder sb, Livro livro) {
    sb.append("Código: ").append(livro.codigo)
            .append(", Nome: ").append(livro.nome)
            .append(", Autor: ").append(livro.autor)
            .append(", Gênero: ").append(livro.genero)
            .append(", Status: ").append(livro.disponivel ? "Disponível" : "Indisponível");
 // Adiciona informações adicionais se o livro não estiver disponível.
    if (!livro.disponivel && livro.clienteAluguel != null && livro.dataAluguel != null && livro.dataDevolucao != null) {
        sb.append(", Cliente: ").append(livro.clienteAluguel)
                .append(", Aluguel: ").append(new SimpleDateFormat("dd/MM/yyyy").format(livro.getDataAluguel()))
                .append(", Devolução: ").append(new SimpleDateFormat("dd/MM/yyyy").format(livro.getDataDevolucao()));
    }

    sb.append("\n");
}


// Método para buscar um livro por código e exibir suas informações.
    private void buscarLivroPorCodigo() {
    	// Solicita o código do livro ao usuário
    	int codigoBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite o código do livro:"));
    	// Obtém o livro correspondente ao código.
    	Livro livroEncontrado = obterLivroPorCodigo(codigoBusca);
    	 // Se o livro for encontrado, obtém o aluguel associado e exibe as informações.
    	if (livroEncontrado != null) {
        Aluguel aluguel = obterAluguelPorCodigoLivro(livroEncontrado.codigo);
        mostrarLivroInfo(livroEncontrado, aluguel);
    } else {
        JOptionPane.showMessageDialog(null, "Livro não encontrado.");
    }
}
 // Método para obter um aluguel com base no código do livro.
    private Aluguel obterAluguelPorCodigoLivro(int codigoLivro) {
    	// Itera sobre a lista de aluguéis para encontrar o aluguel correspondente ao código do livro.
    	for (Aluguel aluguel : alugueis) {
        if (aluguel.codigoLivro == codigoLivro) {
            return aluguel;
        }
    }
    return null; // Retorna null se não encontrar nenhum aluguel correspondente.
}

 // Método para obter um livro com base no código.
    private Livro obterLivroPorCodigo(int codigoLivro) {
    	// Itera sobre a lista de livros para encontrar o livro correspondente ao código.
    	for (Livro livro : livros) {
        if (livro.codigo == codigoLivro) {
            return livro;
        }
    }
    return null;// Retorna null se não encontrar nenhum livro correspondente.
}
 // Método para mostrar as informações de um livro.
    private void mostrarLivroInfo(Livro livro, Aluguel aluguel) {
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    // Constrói uma mensagem com as informações do livro e aluguel (se existir).
    StringBuilder info = new StringBuilder("Livro Encontrado:\n");
    info.append(livro.nome).append(" - ").append(livro.autor).append(" - ").append(livro.genero)
            .append(" - ").append(livro.codigo).append(" - ")
            .append(livro.disponivel ? "Disponível" : "Indisponível");
    // Adiciona a data de devolução se o livro estiver alugado.
    if (aluguel != null) {
        info.append(", Data de Devolução: ").append(dateFormat.format(aluguel.dataDevolucao));
    }
 // Exibe a mensagem com as informações do livro.
    JOptionPane.showMessageDialog(null, info.toString());
}


 // Método para buscar um cliente por nome.
    private void buscarClientePorNome() {
    	// Solicita o nome do cliente ao usuário.
    	String nomeBusca = JOptionPane.showInputDialog("Digite o nome do cliente:");
    	// Itera sobre a lista de clientes para encontrar o cliente correspondente ao nome.
    	for (Cliente cliente : clientes) {
            if (cliente.nome.equalsIgnoreCase(nomeBusca)) {
                JOptionPane.showMessageDialog(null, "Cliente Encontrado:\n" + cliente.nome + " - " + cliente.matricula);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    }
 // Método para buscar um cliente por matrícula. 
    private void buscarClientePorMatricula() {
    	// Solicita a matrícula do cliente ao usuário.       
    	int matriculaBusca = Integer.parseInt(JOptionPane.showInputDialog("Digite a matrícula do cliente:"));
    	// Itera sobre a lista de clientes para encontrar o cliente correspondente à matrícula.
    	for (Cliente cliente : clientes) {
            if (cliente.matricula == matriculaBusca) {
                JOptionPane.showMessageDialog(null, "Cliente Encontrado:\n" + cliente.nome + " - " + cliente.matricula);
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
    }
 // Método para listar livros ordenados alfabeticamente.
    private void listarLivrosOrdenados() {
    	 // Cria uma lista ordenada de livros com base no nome.
    	List<Livro> livrosOrdenados = new ArrayList<>(livros);
    Collections.sort(livrosOrdenados, Comparator.comparing(livro -> livro.nome.toLowerCase()));

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    StringBuilder sb = new StringBuilder("Livros (Ordenados Alfabeticamente):\n");
    for (Livro livro : livrosOrdenados) {
    	// Adiciona informações de cada livro à string de saída.
    	sb.append(livro.nome).append(" - ").append(livro.autor).append(" - ").append(livro.genero)
                .append(" - ").append(livro.codigo).append(" - ")
                .append(livro.disponivel ? "Disponível" : "Indisponível");

        // Adicionar data de devolução, se estiver indisponível
        if (!livro.disponivel) {
            Aluguel aluguel = obterAluguelPorCodigoLivro(livro.codigo);
            if (aluguel != null) {
                sb.append(", Data de Devolução: ").append(dateFormat.format(aluguel.dataDevolucao));
            }
        }

        sb.append("\n");
    }

    outputArea.setText(sb.toString());
}


 // Método para listar clientes ordenados alfabeticamente.
     private void listarClientesOrdenados() {
    	// Cria uma lista ordenada de clientes com base no nome.
    	 List<Cliente> clientesOrdenados = new ArrayList<>(clientes);
    Collections.sort(clientesOrdenados, Comparator.comparing(cliente -> cliente.nome.toLowerCase()));

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    StringBuilder sb = new StringBuilder("Clientes (Ordenados Alfabeticamente):\n");
    for (Cliente cliente : clientesOrdenados) {
    	 // Adiciona informações de cada cliente à string de saída.
    	sb.append(cliente.nome).append(" - ").append(cliente.matricula).append("\n");

        // Exibir informações dos livros alugados pelo cliente
        if (!cliente.alugueis.isEmpty()) {
            sb.append("  Livros Alugados:\n");
            for (Aluguel aluguel : cliente.alugueis) {
                sb.append("    Livro: ").append(aluguel.nomeLivro)
                        .append(", Data de Aluguel: ").append(dateFormat.format(aluguel.dataAluguel))
                        .append(", Data de Devolução: ").append(dateFormat.format(aluguel.dataDevolucao))
                        .append("\n");
            }
        }
    }

    outputArea.setText(sb.toString());
}

  // Método para atualizar a área de saída na interface gráfica com informações de livros e clientes.

    private void atualizarOutput() {
              StringBuilder sb = new StringBuilder();
           // Adiciona informações sobre livros à string de saída.
              sb.append("Livros:\n");
        for (Livro livro : livros) {
            sb.append(livro.nome).append(" - ").append(livro.autor).append(" - ").append(livro.genero)
                    .append(" - ").append(livro.codigo).append(" - ").append(livro.disponivel ? "Disponível" : "Indisponível")
                    .append("\n");
        }
     // Adiciona informações sobre clientes à string de saída.
        sb.append("\nClientes:\n");
        for (Cliente cliente : clientes) {
            sb.append(cliente.nome).append(" - ").append(cliente.matricula).append("\n");
        }
     // Define a string de saída na área de texto da interface gráfica.
        outputArea.setText(sb.toString());
    }
 // Método principal para iniciar a aplicação.
    public static void main(String[] args) {
    	// Inicia a interface gráfica Swing em uma nova thread.
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BibliotecaGUI();
            }
        });
    }
}
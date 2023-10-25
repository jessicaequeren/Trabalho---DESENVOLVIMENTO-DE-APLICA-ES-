import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Produto {
    private String nome;
    private double preco;

    public Produto(String nome, double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco ;
    }
}

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                TelaDeLogin telaDeLogin = new TelaDeLogin();
                telaDeLogin.iniciarTelaDeLogin();
            }
        });
    }
}

class TelaDeLogin {
    private JFrame frame;
    private JTextField usernameField;

    public TelaDeLogin() {
        frame = new JFrame("Tela de Login");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 350);
        frame.setLocationRelativeTo(null); // Centralizar na tela

        // Crie um JPanel com fundo rosa
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cor de fundo personalizada
                g.setColor(new Color(255, 182, 255)); // Rosa claro
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel("Nome Funcionário:");
        usernameField = new JTextField(25);
        JButton loginButton = new JButton("Acessar");

        // Cor de destaque para o botão de login
        loginButton.setBackground(new Color(255, 62, 150)); // Rosa

        panel.add(Box.createVerticalStrut(150)); // Espaço em branco no topo
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(Box.createVerticalStrut(150)); // Espaço em branco entre campos
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty()) {
                    TelaDeVenda telaDeVenda = new TelaDeVenda(username);
                    telaDeVenda.iniciarTela();
                    frame.setVisible(false);
                }
            }
        });

        frame.add(panel);
        frame.pack();
    }

    public void iniciarTelaDeLogin() {
        frame.setVisible(true);
    }
}

class TelaDeVenda {
    private JFrame frame;
    private JComboBox<String> comboBox;
    private JTextArea listaDeItens;
    private JLabel totalLabel;
    private List<Produto> listaDeProdutos;
    private List<Produto> listaProdutosAdicionados;
    private JTextField pesquisaTextField;
    private String username;

    public TelaDeVenda(String username) {
        this.username = username;
        frame = new JFrame("Tela de Venda - Usuário: " + username);

        // Tela de venda com fundo rosa
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Cor de fundo personalizada
                g.setColor(new Color(255, 182, 193)); // Rosa claro
                g.fillRect(0, 0, getWidth(), getHeight());
                frame.setSize(400, 350);
                frame.setLocationRelativeTo(null);
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        comboBox = new JComboBox<>();
        listaDeItens = new JTextArea(20, 20);
        totalLabel = new JLabel("Total: R$ 0.00");
        totalLabel.setFont(new Font("Arial", Font.BOLD, 16));
        listaDeProdutos = new ArrayList<>();
        listaProdutosAdicionados = new ArrayList();
        pesquisaTextField = new JTextField(20);

        listaDeProdutos.add(new Produto("COD1001 - Colar de Esmeralda \"Floresta Encantada\"", 39.99));
        listaDeProdutos.add(new Produto("COD2002 - Anel de Noivado \"Eterno Amor\"", 3000.99));
        listaDeProdutos.add(new Produto("COD3003 - Pulseira de Prata \"Harmonia Lunar\"", 150.99));
        listaDeProdutos.add(new Produto("COD4004 - Pingente de Safira \"Lágrima do Oceano\"", 800.99));
        listaDeProdutos.add(new Produto("COD5005 - Pulseira de Prata Esterlina \"Elo Elegante\"", 200.99));
        listaDeProdutos.add(new Produto("COD6006 - Colar de Diamantes \"Cintilante Celestial\"", 4000.99));
        listaDeProdutos.add(new Produto("COD7007 - Pingente de Topázio \"Céu Azul\"", 300.99));
        listaDeProdutos.add(new Produto("COD8008 - Anel Solitário de Diamante \"Brilho Solitário\"", 4500.99));

        for (Produto produto : listaDeProdutos) {
            comboBox.addItem(produto.getNome());
        }

        JButton adicionarButton = new JButton("Adicionar");
        JButton removerButton = new JButton("Remover");
        JButton calcularTotalButton = new JButton("Calcular");
        JButton pesquisarButton = new JButton("Pesquisar");

        adicionarButton.setBackground(new Color(255, 62, 150)); // Rosa
        removerButton.setBackground(new Color(255, 62, 150)); // Rosa
        calcularTotalButton.setBackground(new Color(255, 62, 150)); // Rosa
        pesquisarButton.setBackground(new Color(255, 62, 150)); // Rosa

        Font buttonFont = new Font("Verdana", Font.BOLD, 20);
        adicionarButton.setFont(buttonFont);
        removerButton.setFont(buttonFont);
        calcularTotalButton.setFont(buttonFont);
        pesquisarButton.setFont(buttonFont);

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) comboBox.getSelectedItem();
                listaDeItens.append(selectedItem + "\n");
                for (Produto produto : listaDeProdutos) {
                    if (produto.getNome().equals(selectedItem)) {
                        listaProdutosAdicionados.add(produto);
                        break;
                    }
                }
            }
        });

        removerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedText = listaDeItens.getSelectedText();
                if (selectedText != null && !selectedText.isEmpty()) {
                    listaDeItens.setText(listaDeItens.getText().replace(selectedText, ""));
                    for (Produto produto : listaProdutosAdicionados) {
                        if (produto.getNome().equals(selectedText.trim())) {
                            listaProdutosAdicionados.remove(produto);
                            break;
                        }
                    }
                }
            }
        });

        calcularTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0.0;
                for (Produto produto : listaProdutosAdicionados) {
                    total += produto.getPreco();
                }
                totalLabel.setText("Total: R$ " + String.format("%.2f", total));
            }
        });

        pesquisarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchTerm = pesquisaTextField.getText().toLowerCase();
                comboBox.removeAllItems();
                for (Produto produto : listaDeProdutos) {
                    if (produto.getNome().toLowerCase().contains(searchTerm)) {
                        comboBox.addItem(produto.getNome());
                    }
                }
            }
        })

//      JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JPanel buttonPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        buttonPanel.add(adicionarButton);
        buttonPanel.add(removerButton);
        buttonPanel.add(calcularTotalButton);
        buttonPanel.add(pesquisarButton);

        panel.add(pesquisaTextField);
        panel.add(buttonPanel);
        panel.add(comboBox);
        panel.add(new JScrollPane(listaDeItens));
        panel.add(totalLabel);

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    public void iniciarTela() {
        frame.setVisible(true);
    }
}

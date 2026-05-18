/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {

        conn = new conectaDAO().connectDB();

        try {
            String sql = "insert into produtos (nome, valor, status) VALUES (?,?,?)";

            prep = conn.prepareStatement(sql);

            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());

            prep.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(null, "Erro ao salvar produto!");
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        conn = new conectaDAO().connectDB();

        try {
            String sql = "SELECT * FROM produtos";

            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();
            
            listagem.clear();
            
            while (rs.next()) {
                ProdutosDTO pd = new ProdutosDTO();

                pd.setId(rs.getInt("id"));
                pd.setNome(rs.getString("nome"));
                pd.setValor(rs.getInt("valor"));
                pd.setStatus(rs.getString("status"));

                listagem.add(pd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao consultar produtos!");
        }
        return listagem;

    }

    public void venderProduto(int id) {
        
        conn = new conectaDAO().connectDB();
        
        try {
           String sql = "UPDATE produtos SET status = ? WHERE id = ?";
           PreparedStatement stmt = conn.prepareStatement(sql);
           stmt.setString(1,"Vendido");
           stmt.setInt(2, id);
           
           stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro ao vender produto");
        }
    }

}

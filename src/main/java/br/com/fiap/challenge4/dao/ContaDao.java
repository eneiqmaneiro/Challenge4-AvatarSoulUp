package br.com.fiap.challenge4.dao;

import br.com.fiap.challenge4.exception.ContaNaoEncontradaException;
import br.com.fiap.challenge4.model.Conta;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ContaDao {

    private final DataSource dataSource;
    private static final String INSERT_SQL = "insert into t_challenge4_conta (cd_conta, nm_conta, ap_conta, em_conta, sh_conta) values (sq_t_challenge4_conta.nextval,?,?,?,?)";
    private static final String SELECT_SQL = "select * from t_challenge4_conta";
    private static final String SELECT_BY_CD_SQL = "select * from t_challenge4_conta where cd_conta = ?";
    private static final String UPDATE_SQL = "update t_challenge4_conta set nm_conta = ?, ap_conta = ?, em_conta = ?, sh_conta = ? where cd_conta = ?";
    private static final String DELETE_SQL = "delete from t_challenge4_conta where cd_conta = ?";

    public ContaDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //GetConta
    private Conta getConta(ResultSet resultSet) throws SQLException {
        int codigo = resultSet.getInt("cd_conta");
        String nome = resultSet.getString("nm_conta");
        String apelido = resultSet.getString("ap_conta");
        String email = resultSet.getString("em_conta");
        String senha = resultSet.getString("sh_conta");
        return new Conta(codigo, nome, apelido, email, senha);
    }

    //CRUD
    //Cadastrar (Create)
    public void cadastrar(Conta conta) throws SQLException {
        try (Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(INSERT_SQL, new String[]{"cd_conta"})){
        stmt.setString(1, conta.getNome());
        stmt.setString(2, conta.getApelido());
        stmt.setString(3, conta.getEmail());
        stmt.setString(4, conta.getSenha());
        stmt.executeUpdate();
        ResultSet resultSet = stmt.getGeneratedKeys();
        if (resultSet.next())
            conta.setCodigo(resultSet.getInt(1));
        }
    }

    //Listar (Read)
    public List<Conta> listar() throws SQLException {
        try (Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(SELECT_SQL)) {
            ResultSet resultSet = stmt.executeQuery();
            List<Conta> lista = new ArrayList<>();
            while (resultSet.next())
                lista.add(getConta(resultSet));
            return lista;
        }
    }

    //Listar (Read com código)
    public Conta pesquisarPorCd(int cd) throws SQLException, ContaNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(SELECT_BY_CD_SQL)){
            stmt.setInt(1, cd);
            ResultSet resultSet = stmt.executeQuery();
            if (!resultSet.next())
                throw new ContaNaoEncontradaException("Conta não encontrada!");
            return getConta(resultSet);
        }
    }

    //Atualizar (Update)
    public void atualizar(Conta conta) throws SQLException, ContaNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(UPDATE_SQL)) {
            stmt.setString(1, conta.getNome());
            stmt.setString(2, conta.getApelido());
            stmt.setString(3, conta.getEmail());
            stmt.setString(4, conta.getSenha());
            stmt.setInt(5, conta.getCodigo());
            int linhas = stmt.executeUpdate();
            if (linhas == 0)
                throw new ContaNaoEncontradaException("Conta não encontrada para atualizar!");
        }
    }

    //Deletar (Delete)
    public void deletar(int cd) throws SQLException, ContaNaoEncontradaException {
        try (Connection conexao = dataSource.getConnection();
        PreparedStatement stmt = conexao.prepareStatement(DELETE_SQL)) {
            stmt.setInt(1, cd);
            int linhas = stmt.executeUpdate();
            if (linhas == 0)
                throw new ContaNaoEncontradaException("Não existe conta para apagar!");
        }
    }



}
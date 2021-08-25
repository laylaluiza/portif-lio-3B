
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Banco {
    static Connection connection = null;
    public static Connection getConnection(){

        if(connection == null){
            try{
                //Class.forName("com.mysql.jdbc.Driver"); //para mysql
                Class.forName("org.postgresql.Driver");//para postgresql
                String host = "localhost";
                String port = "5432";
                String database = "postgres";
                String user = "postgres";
                String pass = "senai";//digitar a senha do seu banco
                //String url = "jdbc:mysql://"+host+":"+port+"/"+database; //para mysql
                String url = "jdbc:postgresql://"+host+":"+port+"/"+database;//para postgresql
                connection = DriverManager.getConnection(url, user, pass);                
               
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void close(){
        if (connection == null){
            throw new RuntimeException("Nenhuma conexão aberta!");
        }
        else{
            try{
                connection.close();
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
                public static void salvar_medico(Medico medico){
            try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO medico (codigo, nome, especialidade, telefone, email, cpf, endereco)values(?, ?, ?, ?, ?, ?, ?)");  
            ps.setInt(1, medico.getCodigo());
            ps.setString(2, medico.getNome());
            ps.setString(3, medico.getEspecialidade());
            ps.setString(4, medico.getTelefone());
            ps.setString(5, medico.getEmail()); 
            ps.setString(6, medico.getCpf()); 
            ps.setString(7, medico.getEndereco());
            ps.execute();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
    
             public static void salvar_paciente (Paciente paciente){
            try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO cliente (nome, id, senha, cpf, email, telefone, convenio) values(?, ?, ?, ?, ?, ?)");         
            ps.setInt(1, paciente.getId());
            ps.setInt(2, paciente.getSenha());
            ps.setString(3, paciente.getNome());
            ps.setString(4, paciente.getCpf());
            ps.setString(5, paciente.getEmail());
            ps.setString(6, paciente.getTelefone()); 
            ps.setBoolean(7, paciente.isConvenio());
            ps.execute();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            } 
             public static void salvar_hospital (Hospital hospital){
            try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("INSERT INTO hospital (codigo_medico, id_paciente) values(?, ?)");         
            ps.setInt(1, hospital.getCodigo_medico());
            ps.setInt(2, hospital.getId_paciente());
            ps.execute();
            } catch (SQLException e) {
            e.printStackTrace();
            }
            }
             public static void deleta_medico(int codigo){
            try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("Delete FROM medico WHERE codigo = ?");
            ps.setInt(1, codigo);
            ps.executeUpdate();
            }catch (SQLException e) {
            e.printStackTrace();
            }
            }
             public static void deleta_paciente(int id){
            try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("Delete FROM paciente WHERE id = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            }catch (SQLException e) {
            e.printStackTrace();
            }
            }
             
        public static void atualizaBanco(Medico medico){
        deleta_medico(medico.getCodigo());
        salvar_medico(medico);
        }
        public static void atualizaBanco(Paciente paciente){
        deleta_paciente(paciente.getSenha());
        salvar_paciente(paciente);
        }
            public static void cria_tabela (String tabela, String... atributo){
        try{
            Connection con = Banco.getConnection();
            String comando = "CREATE TABLE " + tabela + " (";
            
            for (String i : atributo){
                comando = comando + i + ",";
            }
            comando = comando.substring(0, comando.length()-1);
            comando = comando + ");";
            
            System.out.println(comando);
            
            PreparedStatement ps = con.prepareStatement(comando);
            ps.execute();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        }
    public static void visualiza_tabela(String tabela, String... atributo){
        try{
            Connection con = Banco.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM "+ tabela);
            ResultSet rs = ps.executeQuery();
            
            String s = "";
            while(rs.next()){
                for(String i : atributo){
                    s = s + " | " +rs.getString(i);
                }
                s = s + "\n";
            }
            System.out.println(s);

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        }     
        }

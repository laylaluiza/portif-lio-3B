
        package hospital;

        public class Main {

        public static void main(String[] args) {
        
        Banco.cria_tabela("medico", "nome VARCHAR", "codigo int primary key", "especialidade VARCHAR", "telefone VARCHAR", "email VARCHAR", "cpf VARCHAR", 
                                                                                                                                              "endereço VARCHAR");
        
            Medico m1 = new Medico();
                    m1.setNome("Cristiane Silva"); 
                    m1.setCodigo(87-456); 
                    m1.setEspecialidade("Ginecologista"); 
                    m1.setTelefone("(48)94568-1245"); 
                    m1.setEmail("Cristiane@gmail.com"); 
                    m1.setCpf("451.456.578-46");               
                    m1.setEndereco("R. Adolfo Donato da Silva, 96");
                    
        Banco.visualiza_tabela("medico", "nome", "codigo", "especialidade", "telefone", "email", "cpf", "endereço");
            
            
           
        Banco.cria_tabela("paciente", "nome VARCHAR", "id int primary key ", "cpf VARCHAR","email VARCHAR", "telefone VARCHAR", "senha VARCHAR","convenio boolean");     
                                                                                                                                                      
                                    
            Paciente p1 = new Paciente();               
                    p1.setNome("Carolina Costa"); 
                    p1.setId(1);
                    p1.setCpf("458.672.559-60");
                    p1.setEmail("CarolinaC@gmail.com"); 
                    p1.setTelefone("(48)94587-5468");
                    p1.setSenha(45); 
                    p1.setConvenio(true);
                    
        Banco.visualiza_tabela("paciente","nome", "id", "cpf", "email", "telefone", "senha", "convenio");    
        
        
        Banco.cria_tabela("hospital", "codigo_medico int references medico (codigo) ", "id_paciente int references paciente(id)");
        
            Hospital h1 = new Hospital();
                    h1.setCodigo_medico(87-456);
                    h1.setId_paciente(1);
           
        Banco.visualiza_tabela("Hospital", "codigo_medico", "id_paciente");
           
          }
        }

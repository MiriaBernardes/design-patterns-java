# O problema: Muitas regras e códigos complexos.

    public class Orcamento { 
        private double valor; 

        public Orcamento(double valor) { 
          this.valor = valor; 
        } 

        public double getValor() { 
          return valor; 
        } 
    }
    
    public class ICMS {

          public double calculaICMS(Orcamento orcamento) {
              return orcamento.getValor() * 0.1;
          }

      }
      
       public class ISS {

          public double calculaISS(Orcamento orcamento) {
              return orcamento.getValor() * 0.06;
          }

      }
      
      public class CalculadorDeImpostos {

          public void realizaCalculo(Orcamento orcamento, String imposto) {

          if( "ICMS".equals(imposto) ) {

            double icms = new ICMS().calculaICMS(orcamento);
            System.out.println(icms); // imprimirá 50.0

          } else if( "ISS".equals(imposto) ) {

            double iss = new ISS().calculaISS(orcamento);
            System.out.println(iss); // imprimirá 30.0

            }
          }
      }

Um dos problemas do exemplo acima é que toda vez que quisermos calcular um novo imposto deverá ser implementado uma nova condicional para identificar qual imposto será calculado.


# A solução: Polimorfismo

O padrão Strategy é muito útil quando temos um **conjunto de algoritmos similares**, e precisamos alternar entre eles em diferentes pedaços da aplicação.
O Strategy nos oferece uma maneira flexível para escrever diversos algoritmos diferentes, e de passar esses algoritmos para classes clientes que precisam deles. Esses clientes desconhecem qual é o algoritmo "real" que está sendo executado, e apenas mandam o algoritmo rodar. Isso faz com que o código da classe cliente fique bastante desacoplado das implementações concretas de algoritmos, possibilitando assim com que esse cliente consiga trabalhar com N diferentes algoritmos sem precisar alterar o seu código.


    public class CalculadorDeImpostos {

        public void realizaCalculo(Orcamento orcamento, Imposto imposto) {

            double valor = imposto.calcula(orcamento); 

            System.out.println(valor);

        }

      }


      public interface Imposto {
          double calcula(Orcamento orcamento);
      }

      public class ICMS implements Imposto {

          public double calcula(Orcamento orcamento) {
              return orcamento.getValor() * 0.1;
          }

      }

      public class ISS implements Imposto {

          public double calcula(Orcamento orcamento) {
              return orcamento.getValor() * 0.06;
          }

      }

      public class TesteDeImpostos {

        public static void main(String[] args) {
            Imposto iss = new ISS();
            Imposto icms = new ICMS();

            Orcamento orcamento = new Orcamento(500.0);

            CalculadorDeImpostos calculador = new CalculadorDeImpostos();

            // Calculando o ISS
            calculador.realizaCalculo(orcamento, iss);

            // Calculando o ICMS        
            calculador.realizaCalculo(orcamento, icms);
       }
     }

Fonte: **https://cursos.alura.com.br/course/design-patterns**

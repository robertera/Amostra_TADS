package ifpr.pgua.eic.trabbim.modelos;

public class Professor extends Pessoa {
    double salario;

    public Professor(String cpf, String nome, String email, String telefone, double salario){
        super(cpf, nome, email, telefone);
        this.salario = salario;
    }

    public double getSalario(){
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    public String paraTexto(){
        return super.paraTexto()+"; Salario: "+salario;
    }

    public static Professor parse(String professor) {
        return null;
    }

    
}

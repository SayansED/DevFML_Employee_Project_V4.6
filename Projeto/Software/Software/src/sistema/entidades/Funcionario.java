package sistema.entidades;

public class Funcionario {
    // vari�vel destinado ao id do funcion�rio
    private int funcionarioId; 
    // vari�vel destinado ao nome do funcion�rio
    private String funcionarioNome; 
    // vari�vel destinado ao sobrenome do funcion�rio
    private String funcionarioSobrenome; 
    // vari�vel destinado a data de nascimento do funcion�rio
    private String funcionarioDataNascimento;
    // vari�vel destinado ao email do funcion�rio
    private String funcionarioEmail;
    // vari�vel destinado ao cargo do funcion�rio
    private String funcionarioCargo; 
    // vari�vel destinado ao sal�rio atual do funcion�rio
    private Double funcionarioSalario;
    // vari�vel destinado a entrada no sistema
    private String funcionarioDataEntradaSistema;

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int id) {
        this.funcionarioId = id;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String nome) {
        this.funcionarioNome = nome;
    }

    public String getFuncionarioSobrenome() {
        return funcionarioSobrenome;
    }

    public void setFuncionarioSobrenome(String sobrenome) {
        this.funcionarioSobrenome = sobrenome;
    }

    public String getFuncionarioDataNascimento() {
        return funcionarioDataNascimento;
    }

    public void setFuncionarioDataNascimento(String dataNascimento) {
        this.funcionarioDataNascimento = dataNascimento;
    }

    public String getFuncionarioEmail() {
        return funcionarioEmail;
    }

    public void setFuncionarioEmail(String email) {
        this.funcionarioEmail = email;
    }

    public String getFuncionarioCargo() {
        return funcionarioCargo;
    }

    public void setFuncionarioCargo(String cargo) {
        this.funcionarioCargo = cargo;
    }

    public Double getFuncionarioSalario() {
        return funcionarioSalario;
    }

    public void setFuncionarioSalario(Double salario) {
        this.funcionarioSalario = salario;
    }
    
    public String getFuncionarioDataEntradaSistema() {
    	return funcionarioDataEntradaSistema;
    }
    
    public void setFuncionarioDataEntradaSistema(String dataEntradaSistema) {
    	this.funcionarioDataEntradaSistema = dataEntradaSistema;
    }
    
    @Override
    public String toString() {
        return this.funcionarioNome + " " + this.funcionarioSobrenome;
    }    
}

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Grupos;

public class ConsultaCorntoller implements ActionListener {
	 private JTextField  textFieldCodgpCp;
	 private  JTextArea  textAreaCG;
	 private  JTextField textFieldNOMEGP;
		private  JTextField textFieldTEMAGP;
		private  JTextField textFieldAREA;
		private  JTextField textFieldSUBAREA;
		private  JTextField textFieldDATA;
		private  JTextArea  TextAreaNomeAlunosGP;
		private  JTextArea  textAreaRAGP;

	

	public ConsultaCorntoller(JTextField textFieldCodgpCp, JTextArea textAreaCG, JTextField textFieldNOMEGP,
				JTextField textFieldTEMAGP, JTextField textFieldAREA, JTextField textFieldSUBAREA,
				JTextField textFieldDATA, JTextArea textAreaNomeAlunosGP,
				JTextArea textAreaRAGP) {
			super();
			this.textFieldCodgpCp = textFieldCodgpCp;
			this.textAreaCG = textAreaCG;
			this.textFieldNOMEGP = textFieldNOMEGP;
			this.textFieldTEMAGP = textFieldTEMAGP;
			this.textFieldAREA = textFieldAREA;
			this.textFieldSUBAREA = textFieldSUBAREA;
			this.textFieldDATA = textFieldDATA;
			TextAreaNomeAlunosGP = textAreaNomeAlunosGP;
			this.textAreaRAGP = textAreaRAGP;
		}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Pesquisar")) {
			try {
				Consulta();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
		
	}



	private void Consulta() throws IOException {
		Grupos grupo=new Grupos();
		grupo.codigo=textFieldCodgpCp.getText();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.subarea=textFieldSUBAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.alunos=TextAreaNomeAlunosGP.getText();
		grupo.RA=textAreaRAGP.getText();
		
		grupo=buscaGrupo(grupo);
		if(grupo.codigo!= null) {
			textAreaCG.setText("Codigo: "+ grupo.codigo+" - nome: "+grupo.nome +"- tema "+ grupo.tema+ "- area "+ grupo.area+ "- subarea"+ grupo.subarea+ "-data "+ grupo.data+ "- alunos "+ grupo.alunos+ "- Ra "+ grupo.RA);
		}else {
			textAreaCG.setText("Grupo não encontrado");
		}
		
		
	}



	private Grupos buscaGrupo(Grupos grupo) throws IOException {
		 String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		 File arq= new File(path, "grupos.csv");
		 if(arq.exists() && arq.isFile()) {
			 FileInputStream fis=new FileInputStream(arq);
			 InputStreamReader isr= new InputStreamReader(fis);
			 BufferedReader buffer= new BufferedReader(isr);
			 String linha=buffer.readLine();
			 while(linha!=null) {
				 String[] vetLinha=linha.split(";");
				 if(vetLinha[0].equals(grupo.codigo)) {
					 grupo.nome=vetLinha[1];
					 grupo.tema=vetLinha[2];
					 grupo.area=vetLinha[3];
					 grupo.subarea=vetLinha[4];
					 grupo.data=vetLinha[5];
					 grupo.alunos=vetLinha[6];
					 grupo.RA=vetLinha[7];
					 
					 
					 break;
				 }
				 linha=buffer.readLine();
			 }
			 buffer.close();
			 isr.close();
			 fis.close();
		 }
		 return grupo;
		 

	
	}

}

	
	   
	 
	

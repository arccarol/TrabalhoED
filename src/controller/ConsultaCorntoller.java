package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JTextArea;
import javax.swing.JTextField;

import model.Aluno;
import model.Grupos;
import model.Lista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


import model.Grupos;

public class ConsultaCorntoller implements ActionListener {
	 private JTextField  textFieldCodgpCp;
	 private  JTextField textFieldNOMEGP;
		private  JTextField textFieldTEMAGP;
		private  JTextField textFieldAREA;
		private  JTextField textFieldSUBAREA;
		private  JTextField textFieldDATA;
		private  JTextField textFieldCOD;
		private  JTextField textField_ra01;
		private  JTextField textField_ra02;
		private  JTextField textField_ra03;
		private  JTextField textField_ra04;
		private JTextArea textAreaConsultaOrientacao;
		private JTextField textFieldNome;
		private  JTextField textFieldRa;
	


	public ConsultaCorntoller(JTextField textFieldCodgpCp, JTextField textFieldTEMAGP,
				JTextField textFieldSUBAREA, JTextField textFieldDATA, JTextField textFieldNOMEGP,
				JTextField textFieldAREA, JTextField textField_ra01, JTextField textField_ra02, JTextField textField_ra03, 
				JTextField textField_ra04,  JTextField textFieldCOD, JTextArea textAreaConsultaOrientacao, JTextField textFieldNome, JTextField textFieldRa ) {
		
		super();
		this.textFieldNOMEGP = textFieldNOMEGP;
		this.textFieldTEMAGP = textFieldTEMAGP;
		this.textFieldAREA = textFieldAREA;
		this.textFieldSUBAREA = textFieldSUBAREA;
		this.textFieldDATA = textFieldDATA;
		this.textFieldCOD = textFieldCOD;
		this.textFieldCodgpCp = textFieldCodgpCp; // onde digita o código do grupo
		this.textField_ra01 = textField_ra01;
		this.textField_ra02 = textField_ra02;
		this.textField_ra03 = textField_ra03;
		this.textField_ra04 = textField_ra04;
	this.textAreaConsultaOrientacao = textAreaConsultaOrientacao;
	this.textFieldNome = textFieldNome;
	this.textFieldRa = textFieldRa;
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
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	}
		
	}
	private void Consulta() throws Exception {
		var grupo=new Grupos();
		var aluno = new Aluno();
		grupo.nome=textFieldNOMEGP.getText();
		grupo.tema=textFieldTEMAGP.getText();
		grupo.area=textFieldAREA.getText();
		grupo.subarea=textFieldSUBAREA.getText();
		grupo.data=textFieldDATA.getText();
		grupo.codigo=textFieldCodgpCp.getText();
		grupo.Ra1 = textField_ra01.getText();
		grupo.Ra2 = textField_ra02.getText();
		grupo.Ra3 = textField_ra03.getText();
		grupo.Ra4 = textField_ra04.getText();
	    aluno.Nome = textFieldNome.getText();
	    aluno.Ra = textFieldRa.getText();

	Lista ConGP=new Lista();
	
	if(!grupo.codigo.equals("")) {
		grupo=buscaCodorientacao(grupo.codigo);
	
	if(grupo!= null) {
		textAreaConsultaOrientacao.setText("Codigo: "+ grupo.codigo+" - area: "+grupo.area +" - tema "+ grupo.tema+ "-subarea "+ grupo.subarea+ "- data "+ grupo.data+  "- nome "+ grupo.nome+ "-RA ");
	}
	
	}else if(!grupo.area.equals("")) {
		ConGP = buscaArea(grupo.area);
	}else if(!grupo.tema.equals("")) {
		ConGP = buscaTema(grupo.tema);
	}else if(!grupo.subarea.equals("")) {
		ConGP = buscaSubarea(grupo.subarea);
	}else if(!grupo.data.equals("")) {
		ConGP = buscaData(grupo.data);
	}else if(!grupo.nome.equals("")) {
		ConGP = buscaNome(grupo.nome);
	}else if(!grupo.Ra1.equals("")) {
		ConGP = buscaRa(grupo.Ra1);
	}else if(!grupo.Ra2.equals("")) {
		ConGP = buscaRa(grupo.Ra2);
	}else if(!grupo.Ra3.equals("")) {
		ConGP = buscaRa(grupo.Ra3);
	}else if(!grupo.Ra4.equals("")) {
		ConGP = buscaRa(grupo.Ra4);
	} else {
		JOptionPane.showMessageDialog(null, "Prencha um campo", "ERRO!", JOptionPane.ERROR_MESSAGE);
	}
	int tamanholista = ConGP.size();
	StringBuffer buffer = new StringBuffer();
	if(tamanholista>0) {
	for(int i=0; i<tamanholista;i++) {
		Grupos grupo1 =(Grupos) ConGP.get(i);
		buffer.append("Codigo: "+ grupo1.codigo+" - area: "+grupo1.area +"- tema "+ grupo1.tema+ "-subarea "+ grupo1.subarea+ "- data"+ grupo1.data+  "- nome "+ grupo1.nome+ "RA "+ grupo1.RA+ "Alunos"+ grupo1.alunos);
	}
	textAreaConsultaOrientacao.setText(buffer.toString());
	}
}



	private Grupos buscaCodorientacao(String codigo) throws IOException {
		Grupos grupo=new Grupos();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[0].equals(codigo)) {
					grupo.codigo=vetLinha[0];
					grupo.area=vetLinha[1];
					grupo.tema=vetLinha[2];
					grupo.subarea=vetLinha[3];
					grupo.data=vetLinha[4];
					grupo.nome=vetLinha[5];
					grupo.RA=vetLinha[6];
					grupo.alunos=vetLinha[7];

					break;
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		if(grupo.codigo==null) {
			grupo=null;
		}
		return grupo;
	}
	private Lista buscaArea(String area) throws IOException {
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[1].equals(area)) {
					Grupos grupo= new Grupos();
					grupo.codigo=vetLinha[0];
					grupo.area=vetLinha[1];
					grupo.tema=vetLinha[2];
					grupo.subarea=vetLinha[3];
					grupo.data=vetLinha[4];
					grupo.nome=vetLinha[5];
					grupo.RA=vetLinha[6];
					grupo.alunos=vetLinha[7];

				ConGP.addFirst(ConGP);
				
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		
		return ConGP;
	}
	
	private Lista buscaTema(String tema) throws IOException {
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[2].equals(tema)) {
					Grupos grupo= new Grupos();
					grupo.codigo=vetLinha[0];
					grupo.area=vetLinha[1];
					grupo.tema=vetLinha[2];
					grupo.subarea=vetLinha[3];
					grupo.data=vetLinha[4];
					grupo.nome=vetLinha[5];
					grupo.RA=vetLinha[6];
					grupo.alunos=vetLinha[7];

				ConGP.addFirst(ConGP);
				
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		
		return ConGP;
	}
	
	private Lista buscaSubarea(String subarea) throws IOException {
		
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[3].equals(subarea)) {
					Grupos grupo= new Grupos();
					grupo.codigo=vetLinha[0];
					grupo.area=vetLinha[1];
					grupo.tema=vetLinha[2];
					grupo.subarea=vetLinha[3];
					grupo.data=vetLinha[4];
					grupo.nome=vetLinha[5];
					grupo.RA=vetLinha[6];
					grupo.alunos=vetLinha[7];

				ConGP.addFirst(ConGP);
				
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		
		return ConGP;
	}
	
	private Lista buscaData(String data) throws IOException {
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
		FileInputStream fis=new FileInputStream(arq);
		InputStreamReader isr= new InputStreamReader(fis);
		BufferedReader buffer= new BufferedReader(isr);
		String linha=buffer.readLine();
		while(linha!=null) {
			String[] vetLinha=linha.split(";");
			if(vetLinha[4].equals(data)) {
				Grupos grupo= new Grupos();
				grupo.codigo=vetLinha[0];
				grupo.area=vetLinha[1];
				grupo.tema=vetLinha[2];
				grupo.subarea=vetLinha[3];
				grupo.data=vetLinha[4];
				grupo.nome=vetLinha[5];
				grupo.RA=vetLinha[6];
				grupo.alunos=vetLinha[7];

			ConGP.addFirst(ConGP);
			
			}
			linha=buffer.readLine();

		}           
		buffer.close();
		isr.close();
		fis.close();
	}
		return ConGP;
	
	}
	private Lista buscaNome(String nome) throws IOException {
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
		FileInputStream fis=new FileInputStream(arq);
		InputStreamReader isr= new InputStreamReader(fis);
		BufferedReader buffer= new BufferedReader(isr);
		String linha=buffer.readLine();
		while(linha!=null) {
			String[] vetLinha=linha.split(";");
			if(vetLinha[5].equals(nome)) {
				Grupos grupo= new Grupos();
				grupo.codigo=vetLinha[0];
				grupo.area=vetLinha[1];
				grupo.tema=vetLinha[2];
				grupo.subarea=vetLinha[3];
				grupo.data=vetLinha[4];
				grupo.nome=vetLinha[5];
				grupo.RA=vetLinha[6];
				grupo.alunos=vetLinha[7];

			ConGP.addFirst(ConGP);
			
			}
			linha=buffer.readLine();

		}           
		buffer.close();
		isr.close();
		fis.close();
	}
		return ConGP;
	
	}
	private Lista buscaRa(String Ra) throws IOException {
		Lista ConGP=new Lista();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "grupos.csv");
		if(arq.exists() && arq.isFile()) {
		FileInputStream fis=new FileInputStream(arq);
		InputStreamReader isr= new InputStreamReader(fis);
		BufferedReader buffer= new BufferedReader(isr);
		String linha=buffer.readLine();
		while(linha!=null) {
			String[] vetLinha=linha.split(";");
			if(vetLinha[6].equals(Ra)) {
				Grupos grupo= new Grupos();
				grupo.codigo=vetLinha[0];
				grupo.area=vetLinha[1];
				grupo.tema=vetLinha[2];
				grupo.subarea=vetLinha[3];
				grupo.data=vetLinha[4];
				grupo.nome=vetLinha[5];
				grupo.RA=vetLinha[6];

			ConGP.addFirst(ConGP);
			
			}
			linha=buffer.readLine();

		}           
		buffer.close();
		isr.close();
		fis.close();
	}
		return ConGP;
	
	}
	
		
	}	
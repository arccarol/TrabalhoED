package controller;

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

import model.Lista;
import model.Orientacao;
import model.Pilha;

public class CosultaOrientacao implements ActionListener {
	private JTextField textFieldCodigoOrientacao;
	private  JTextArea textAreaConsultaOrientacao;
	private  JTextField textFieldCoDGRUPOOrientacao;
	private  JTextField textFieldTituloOrientacao;
	private  JTextField textFieldDataOrientacaoOrientacao;
	private  JTextArea  TextArea;



	public CosultaOrientacao(JTextField textFieldCodigoOrientacao, JTextArea textAreaConsultaOrientacao,
			JTextField textFieldCoDGRUPOOrientacao,
			JTextField textFieldTituloOrientacao, JTextField textFieldDataOrientacaoOrientacao,
			JTextArea textArea) {
		super();
		this.textFieldCodigoOrientacao = textFieldCodigoOrientacao;
		this.textAreaConsultaOrientacao = textAreaConsultaOrientacao;
		this.textFieldCoDGRUPOOrientacao = textFieldCoDGRUPOOrientacao;
		this.textFieldTituloOrientacao = textFieldTituloOrientacao;
		this.textFieldDataOrientacaoOrientacao = textFieldDataOrientacaoOrientacao;
		TextArea = textArea;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String cdm = e.getActionCommand();
		if(cdm.equals("Ultima")) {
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
		Orientacao orientacao=new Orientacao();
		orientacao.codOrientacao=textFieldCodigoOrientacao.getText();
		orientacao.codigoGP=textFieldCoDGRUPOOrientacao.getText();
		orientacao.titulo=textFieldTituloOrientacao.getText();
		orientacao.data=textFieldDataOrientacaoOrientacao.getText();
		orientacao.descricao=TextArea.getText();

		Lista Orientacoes= new Lista();
       Pilha p = new Pilha();
		if(!orientacao.codOrientacao.equals("")) {
			p=buscaCodorientacao(orientacao.codOrientacao);	

			if(orientacao!=null) {
				textAreaConsultaOrientacao.setText(" Codigo Orientacao = "+ orientacao.codOrientacao+ " Codigo Grupo = " + orientacao.codigoGP+" Data = "+orientacao.data+ " Titulo = "+ orientacao.titulo+ " Descricao = "+  orientacao.descricao);
			}


		}else if (orientacao.codigoGP.equals("")) {
			p=BuscaCodGP(orientacao.codigoGP);

		}else if (!orientacao.titulo.equals("")) {
			p=BuscaTitulo(orientacao.titulo);

		}else if(!orientacao.data.equals("")) {
			p=BuscaData(orientacao.data);

		}else if(!orientacao.descricao.equals("")) {
			p=BucarDescricao(orientacao.descricao);
		}else {
			JOptionPane.showMessageDialog(null, "Preencha um campo ", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		int tamanhoLista=Orientacoes.size();
		StringBuffer buffer =new StringBuffer();
		if(tamanhoLista>0) {
			for( int i=0; i< tamanhoLista; i++) {
				Orientacao O=(Orientacao) Orientacoes.get(i);
				buffer.append(" Codigo Orientacao = "+ O.codOrientacao+ " Codigo Grupo = " + O.codigoGP +" Data = "+ O.data+ "Titulo = "+ O.titulo+ " Descricao = "+  O.descricao+"\r\n");

			}
			TextArea.setText(buffer.toString());



		}



	}


	private Pilha buscaCodorientacao(String codOrientacao) throws IOException {
		Pilha p = new Pilha();
		Orientacao orientacao=new Orientacao();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[0].equals(codOrientacao)) {
					orientacao.codOrientacao=vetLinha[0];
					orientacao.codigoGP=vetLinha[1];
					orientacao.titulo=vetLinha[2];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];
                    p.push(orientacao.descricao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}
		if(orientacao.codOrientacao==null) {
			orientacao=null;
		}
		return p;
	}	
	
	private Pilha BucarDescricao(String descricao) throws IOException {
		Pilha p = new Pilha();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[4].equals(descricao)) {
					Orientacao orientacao= new Orientacao();
					orientacao.codOrientacao=vetLinha[0];
					orientacao.codigoGP=vetLinha[1];
					orientacao.titulo=vetLinha[2];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];

					p.push(orientacao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}

		return p;
	}



	private Pilha BuscaData(String data) throws IOException {
		Pilha p = new Pilha();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[3].equals(data)) {
					Orientacao orientacao= new Orientacao();
					orientacao.codOrientacao=vetLinha[0];
					orientacao.codigoGP=vetLinha[1];
					orientacao.titulo=vetLinha[2];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];

					p.push(orientacao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}

		return p;
	}




	private Pilha BuscaTitulo(String titulo) throws IOException {
		Pilha p = new Pilha();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[2].equals(titulo)) {
					Orientacao orientacao= new Orientacao();
					orientacao.codOrientacao=vetLinha[0];
					orientacao.codigoGP=vetLinha[1];
					orientacao.titulo=vetLinha[2];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];

					p.push(orientacao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}

		return p;
	}




	private Pilha BuscaCodGP(String codigoGP) throws IOException {
		Pilha p = new Pilha();
		String path= System.getProperty ("user.home") + File.separator + "SistemaCadastro";
		File arq= new File(path, "Orientacao.csv");
		if(arq.exists() && arq.isFile()) {
			FileInputStream fis=new FileInputStream(arq);
			InputStreamReader isr= new InputStreamReader(fis);
			BufferedReader buffer= new BufferedReader(isr);
			String linha=buffer.readLine();
			while(linha!=null) {
				String[] vetLinha=linha.split(";");
				if(vetLinha[1].equals(codigoGP)) {
					Orientacao orientacao= new Orientacao();
					orientacao.codOrientacao=vetLinha[0];
					orientacao.codigoGP=vetLinha[1];
					orientacao.titulo=vetLinha[2];
					orientacao.data=vetLinha[3];
					orientacao.descricao=vetLinha[4];

					p.push(orientacao);
				}
				linha=buffer.readLine();

			}           
			buffer.close();
			isr.close();
			fis.close();
		}

		return p;
	}	
}
package cliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

	public static void main(String[] args) {

		while (true) {
			String sentence;
			String response;

			// Exemplo: create | 4,Filipe,...
			// sair
			System.out.println("Esperando mensagem no seguinte formato: operation | {parameters} or sair");

			try {

				// Cria um buffer que armazenará as informações de entrada do teclado
				BufferedReader inFromUSer = new BufferedReader(new InputStreamReader(System.in));

				// Cria um Socket cliente passando como parâmetro o ip e a porta do servidor
				Socket client = new Socket("192.168.0.105", 40000);

				// Cria um stream de saída
				DataOutputStream outToServer = new DataOutputStream(client.getOutputStream());

				// Cria um buffer que armazenará as informações retornadas pelo servidor
				BufferedReader inFromServer = new BufferedReader(new InputStreamReader(client.getInputStream()));

				// Atribui as informações armazenadas no buffer do teclado à variável "sentence"
				sentence = inFromUSer.readLine();

				// Disponibiliza as informações contidas em "sentence" para a stream de saída do
				// cliente
				outToServer.writeBytes(sentence + "\n");

				// Atribui as informações modificadas pelo servidor na variável
				// "modifiedSentence"
				response = inFromServer.readLine();

				// Imprime no console do cliente a informação retornada pelo servidor
				System.out.println("HTTP response server: " + response);
				System.out.println();
				// Fecha o Socket
				if (sentence.contains("sair")) {
					break;
				}
				client.close();

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
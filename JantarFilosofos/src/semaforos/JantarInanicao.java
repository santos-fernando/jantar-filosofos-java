package semaforos;
import java.io.IOException;
import java.util.concurrent.Semaphore;

/**
 * Implementação de um jantar com 5 filósofos sujeitos a inanição (starvation).
 * 
 * Cada garfo é representado por um semáforo mutex.
 * 
 * @author Fernando Santos (github.com/santos-fernando)
 */
public class JantarInanicao {

	public static void main(String[] args) throws IOException, InterruptedException {
		Semaphore[] garfos = new Semaphore[5];
		
		for(int i = 0; i < garfos.length; i++) {
			garfos[i] = new Semaphore(1);
		}

		// criação dos filósofos
		FilosofoInanicao fil0 = new FilosofoInanicao(0, garfos[0], garfos[4]);
		FilosofoInanicao fil1 = new FilosofoInanicao(1, garfos[1], garfos[0]);
		FilosofoInanicao fil2 = new FilosofoInanicao(2, garfos[2], garfos[1]);
		FilosofoInanicao fil3 = new FilosofoInanicao(3, garfos[3], garfos[2]);
		FilosofoInanicao fil4 = new FilosofoInanicao(4, garfos[4], garfos[3]);
		
		// execução dos filósofos
		fil0.start();
		fil1.start();
		fil2.start();
		fil3.start();
		fil4.start();

		// aguardar o usuario pressionar qualquer tecla 
		System.in.read();

		// desativar os filósofos
		fil0.desativar();
		fil1.desativar();
		fil2.desativar();
		fil3.desativar();
		fil4.desativar();

		// aguardar todos os filósofos finalizarem
		fil0.join();
		fil1.join();
		fil2.join();
		fil3.join();
		fil4.join();

		// imprimir quantas porções cada filósofo comeu durante o jantar
		System.out.println("Filosofo 0. Porções comidas até agora: " + fil0.getPorcoesComidas());
		System.out.println("Filosofo 1. Porções comidas até agora: " + fil1.getPorcoesComidas());
		System.out.println("Filosofo 2. Porções comidas até agora: " + fil2.getPorcoesComidas());
		System.out.println("Filosofo 3. Porções comidas até agora: " + fil3.getPorcoesComidas());
		System.out.println("Filosofo 4. Porções comidas até agora: " + fil4.getPorcoesComidas());		
	}
}

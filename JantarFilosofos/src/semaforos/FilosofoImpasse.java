package semaforos;

/**
 * Implementação de um Filósofo sujeito a impasses (deadlocks). 
 * Este filósofo pega um garfo e em seguida tenta pegar o outro sem liberar o anterior.
 * 
 * @author Fernando Santos (github.com/santos-fernando)
 */
import java.util.concurrent.Semaphore;

import base.Filosofo;

public class FilosofoImpasse extends Filosofo {
	private Semaphore garfoEsquerdo;
	private Semaphore garfoDireito;

	public FilosofoImpasse(int numero, Semaphore garfoEsq, Semaphore garfoDir) {
		super(numero);
		this.garfoEsquerdo = garfoEsq;
		this.garfoDireito = garfoDir;
	}

	@Override
	public void run() {
		try {
			while (ativo) {
				pensar();
				boolean comeu = false;
				do {
					System.out.println("Filosofo " + numero + " tentando pegar garfo esquerdo.");
					garfoEsquerdo.acquire();
					System.out.println("Filosofo " + numero + " tentando pegar garfo direito.");
					garfoDireito.acquire();

					comer();
					comeu = true;
					garfoEsquerdo.release();
					garfoDireito.release();
				} while (!comeu);
			}
		} catch (InterruptedException e) {
			System.out.println("Filosofo " + numero + " interrompido. Abortando.");
			e.printStackTrace();
		}
	}
}

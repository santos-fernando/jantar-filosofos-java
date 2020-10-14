package semaforos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import base.Filosofo;

/**
 * Implementação de um Filósofo funcional, livre de impasses e com pouca chance
 * de inanição. Nesta implementação cada filósofo aguarda um tempo aleatório
 * antes de fazer a próxima tentativa de obter os garfos.
 * 
 * Eventualmente inanição poderá ocorrer em algum momento, mas como o tempo de
 * espera é aleatório, em algum momento os filófosos superarão a inanição.
 * 
 * @author Fernando Santos (github.com/santos-fernando)
 */
public class FilosofoFuncional extends Filosofo {
	private Semaphore garfoEsquerdo;
	private Semaphore garfoDireito;

	public FilosofoFuncional(int numero, Semaphore garfoEsq, Semaphore garfoDir) {
		super(numero);
		this.garfoEsquerdo = garfoEsq;
		this.garfoDireito = garfoDir;
	}

	@Override
	public void run() {
		Random rand = new Random();
		try {
			while (ativo) {
				pensar();
				boolean comeu = false;
				do {
					garfoEsquerdo.acquire();
					boolean pegou = garfoDireito.tryAcquire(500, TimeUnit.MILLISECONDS);
					if (pegou) {
						comer();
						comeu = true;
						// libera os 2 garfos
						garfoDireito.release();
						garfoEsquerdo.release();
					} else {
						// libera o garfo que havia tomado posse e aguarda
						garfoEsquerdo.release();
						sleep(100 + rand.nextInt(400)); // solução funcional é aguardar tempo aleatório,
														// diminui chance de starvation
					}
				} while (!comeu);
			}
		} catch (InterruptedException e) {
			System.out.println("Filosofo " + numero + " interrompido. Abortando.");
			e.printStackTrace();
		}
	}
}

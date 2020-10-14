package semaforos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import base.Filosofo;

/**
 * Implementação de um Filósofo sujeito a inanição (starvation). Este filósofo
 * pega o primeiro garfo, e depois tenta pegar o próximo. Se depois de um tempo
 * não conseguir pegar o segundo garfo, então desiste, libera o primeiro, e
 * aguarda um tempo fixo antes de tentar novamente.
 * 
 * Esta implementação está sujeita a inanição pois, se os filósofos pegarem o
 * primeiro garfo todos ao mesmo tempo, então nenhum vai conseguir pegar o
 * segundo e vão devolver o primeiro e aguardar um tempo fixo. Todos os
 * filósofos aguardarão o mesmo tempo e tentarão pegar novamente os garfos
 * simultaneamente, ocasionando a situação de inanição: eles não ficam
 * bloqueados, mas não conseguem progredir.
 * 
 * @author Fernando Santos (github.com/santos-fernando)
 */
public class FilosofoInanicao extends Filosofo {
	private Semaphore garfoEsquerdo;
	private Semaphore garfoDireito;

	public FilosofoInanicao(int numero, Semaphore garfoEsq, Semaphore garfoDir) {
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
						sleep(100); // se todos aguardarem o mesmo tempo, há chance de starvation
					}
				} while (!comeu);
			}
		} catch (InterruptedException e) {
			System.out.println("Filosofo " + numero + " interrompido. Abordando.");
			e.printStackTrace();
		}
	}
}

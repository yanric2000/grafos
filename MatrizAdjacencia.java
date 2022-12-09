import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MatrizAdjacencia {
    public static String obtemVerticeMaiorGrau(int[][] grafo, String vertices[]) {
        int vertice = 0;
        int maior = 0;
        
        for (int i = 0; i < grafo.length; i++) {
            int grau = obtemGrau(grafo, vertices, vertices[i]);

            if (grau > maior) {
                vertice = i;
                maior = grau;
            }
        }

        return vertices[vertice];
    }

    public static String obtemVerticeMenorGrau(int[][] grafo, String vertices[]) {
        int vertice = 99999;
        int maior = 99999;
        
        for (int i = 0; i < grafo.length; i++) {
            int grau = obtemGrau(grafo, vertices, vertices[i]);

            if (grau < maior) {
                vertice = i;
                maior = grau;
            }
        }

        return vertices[vertice];
    }

    public static int obtemIndiceVertice(String vertices[], String vertice) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertice)) {
                return i;
            }
        }

        return -1;
    }

    public static int obtemGrau(int[][] grafo, String[] vertices, String vertice) {
        int i = obtemIndiceVertice(vertices, vertice);
        int grau = 0;
        
        for (int j = 0; j < grafo.length; j++) {
            if (grafo[i][j] > 0 || grafo[j][i] > 0) {
                grau++;
            }
        }

        return grau;
    }


    public static int ma(int i, int j, int[][] ma){
        if(i < j)
            return ma[i][j];
        else
            return ma[j][i];
    }

    public static int[] buscaLateral(int inicio, int[][] grafo, int tamanho){
        int[] distancias = new int[tamanho];
        Queue<Integer> fila = new LinkedList<>();
    
        for(int i = 0; i < tamanho; i++)
            distancias[i] = -1;
    
        distancias[inicio] = 0;
        fila.add(inicio);
    
        while(!fila.isEmpty()){
            int atual = fila.remove();
            for(int i = 0; i < tamanho; i++)
                if(ma(atual, i, grafo) == 1 && distancias[i] == -1){
                    distancias[i] = distancias[atual] + 1;
                    fila.add(i);
                }
        }
        return distancias;
    }

    public static int deParaMenorCaminho(int inicio, int destino, int[][] grafo, int tamanho, String[] vertices) {
        var menorQuantidadePercorrida = buscaLateral(inicio, grafo, tamanho);

        return menorQuantidadePercorrida[destino];
    }

    public static void encontrarTodosOsCaminhos(int origem, int destino, int[][] matrizAdjacencia, String[] estados) {
        // pilha para armazenar os estados que serão visitados
        Stack<Integer> stack = new Stack<>();
        
        int[] caminho = new int[matrizAdjacencia.length];
        
        // marca o estado inicial como visitado
        boolean[] visitados = new boolean[matrizAdjacencia.length];
        visitados[destino] = true;
        
        stack.push(destino);

        while (!stack.isEmpty()) {
            int estado = stack.pop();
            
            for (int i = 0; i < matrizAdjacencia[estado].length; i++) {
                if (matrizAdjacencia[estado][i] == 1 && !visitados[i]) {
                    visitados[i] = true;
                    stack.push(i);
                    caminho[i] = estado;
                    
                    if (i == origem) {
                        System.out.print("Caminho possível: ");
                        estado = origem;

                        while (estado != destino) {
                            System.out.print(estados[estado] + " -> ");
                            estado = caminho[estado];
                        }

                        System.out.println(estados[destino]);
                        
                        // volta ao estado anterior e continua a busca
                        stack.pop();
                        visitados[i] = false;
                    }
                }
            }
        }
        
    }

    

    public static void main(String args[]) {
        // 1 - 30 arestas(grafo regular todas as vértices tem o mesmo grau)
        int[][] grafo = {
            // RS
            {
                0, 1, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // SC
            {
                1, 0, 1,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // PR
            {
                0, 1, 0,
                1, 0, 1, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // SP
            {
                0, 0, 1,
                0, 1, 1, 0, 0,
                1, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // RJ
            {
                0, 0, 0,
                1, 0, 1, 1, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // MG
            {
                0, 0, 0,
                1, 1, 0, 1, 0,
                1, 0, 1,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // ES
            {
                0, 0, 0,
                0, 1, 1, 0, 0,
                0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // DF
            {
                0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // MS
            {
                0, 0, 1,
                1, 0, 1, 0, 0,
                0, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // MT
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                1, 0, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 0, 0, 1, 0, 1,
            },
            // GO
            {
                0, 0, 0,
                0, 0, 1, 0, 0,
                0, 0, 0,
                1, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 0, 0, 1, 0, 1,
            },
            // BA
            {
                0, 0, 0,
                0, 0, 1, 1, 0,
                0, 0, 1,
                0, 1, 1, 1, 0, 0, 0, 1, 1,
                1, 0, 0, 0, 0, 0, 0,
            },
            // SE
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                1, 0, 3, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // AL
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                1, 1, 0, 1, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // PE
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                1, 0, 1, 0, 0, 0, 1, 1, 0,
                0, 1, 0, 0, 0, 0, 0,
            },
            // PB
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 1, 0, 1, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // RN
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 1, 0, 1, 0, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // CE
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 1, 1, 1, 0, 1, 0,
                0, 0, 0, 0, 0, 0, 0,
            },
            // PI
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                1, 0, 0, 1, 0, 0, 1, 0, 1,
                1, 0, 0, 0, 0, 0, 0,
            },
            // MA
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 1, 0,
                1, 1, 0, 0, 0, 0, 0,
            },
            // TO
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 1, 1,
                1, 0, 0, 0, 0, 0, 0, 1, 1,
                0, 1, 0, 0, 0, 0, 0,
            },
            // PA
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 1,
                1, 0, 1, 1, 1, 0, 0,
            },
            // AP
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 0, 0, 0,
            },
            // RR
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 0, 1, 0, 0,
            },
            // AM
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 1, 0, 1, 0, 1, 1,
            },
            // AC
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 0, 1,
            },
            // RO
            {
                0, 0, 0,
                0, 0, 0, 0, 0,
                0, 1, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0,
                0, 0, 0, 0, 1, 1, 0,
            },
        };

        String[] vertices = {
            "RS", "SC", "PR",
            "SP", "RJ", "MG", "ES", "DF",
            "MS", "MT", "GO",
            "BA", "SE", "AL", "PE", "PB", "RN", "CE", "PI", "MA",
            "TO", "PA", "AP", "RR", "AM", "AC", "RO"
        };

        String verticeMaiorGrau = obtemVerticeMaiorGrau(grafo, vertices);
        int maiorGrau = obtemGrau(grafo, vertices, verticeMaiorGrau);

        String verticeMenorGrau = obtemVerticeMenorGrau(grafo, vertices);
        int menorGrau = obtemGrau(grafo, vertices, verticeMenorGrau);
        

        System.out.println("");
        System.out.println("##############################");
        System.out.println("");

        System.out.println("Vértice com maior grau: " + verticeMaiorGrau);
        System.out.println("Estado com maior número de vizinhos " + verticeMaiorGrau  + ": " + maiorGrau);

        System.out.println("");
        System.out.println("##############################");
        System.out.println("");

        System.out.println("Vértice com menor grau: " + verticeMenorGrau);
        System.out.println("Estado com maior número de vizinhos " + verticeMenorGrau  + ": " + menorGrau);

        System.out.println("");
        System.out.println("##############################");
        System.out.println("");

        int saoPaulo = 3;
        int pernambuco = 14;

        int menorQuantidadePercorrida = deParaMenorCaminho(saoPaulo, pernambuco , grafo, vertices.length, vertices);

        System.out.println("De " + vertices[saoPaulo] + " para " + vertices[pernambuco] + " a distância é de " + menorQuantidadePercorrida + " estado(s)");

        System.out.println("");
        System.out.println("##############################");
        System.out.println("");


        encontrarTodosOsCaminhos(saoPaulo, pernambuco, grafo, vertices);

        System.out.println("");
    }
}
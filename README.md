# Breadcrumbskotlin-arrays-e-null-references

O objetivo neste estudo é enfatizar a **diferença no tratamento de referências nulas** e o comportamento de **arrays contendo valores nulos** entre Kotlin e Java, além de ilustrar como Kotlin lida com o problema de **NullPointerException** de maneira mais segura e explícita.

---

### **Ponto Central: Referências Nulas e Arrays**

1. **Referências Nulas (Null References):**
   - No **Java**, referências nulas podem levar a `NullPointerException` (NPE) se forem acessadas diretamente sem verificação.
   - No **Kotlin**, referências nulas são tratadas com segurança por meio de:
     - **Tipos Nullable (`String?`)**
     - **Operadores de segurança (`?.`, `!!`)**

2. **Arrays com Valores Nulos:**
   - No **Java**, arrays de objetos (`Integer[]`, `String[]`) podem conter valores `null` por padrão, sem restrições.
   - No **Kotlin**, o autor demonstra o uso de `arrayOfNulls`, que cria um array explicitamente capaz de armazenar valores `null`.

---

### **Análise do Código**

#### **1. Código Java**

```java
public class NullReferences {

    public static void main(String[] args) {
        String str = null;
        str.toUpperCase(); // Gera NullPointerException
    }
}
```

- **Problema:**  
  O código tenta acessar um método (`toUpperCase()`) em uma referência `null`. Isso resulta em uma **NullPointerException**, uma das exceções mais comuns e frustrantes no Java.

- **Lição:**  
  O Java não possui ferramentas nativas para lidar com referências nulas de maneira segura. É responsabilidade do programador adicionar verificações manuais:
  ```java
  if (str != null) {
      str.toUpperCase();
  }
  ```

---

#### **2. Código Kotlin**

```kotlin
fun main(args: Array<String>) {
    val nullableInts = arrayOfNulls<Int>(5) // Array que aceita null
    for (i in nullableInts) {
        println(i) // Todos os elementos inicializados como null
    }
    println(nullableInts[3].toString()) // Gera NullPointerException
}
```

- **Comportamento:**
  - `arrayOfNulls<Int>(5)` cria um array de tamanho 5 onde todos os elementos são inicializados com `null`.
  - O laço `for` imprime `null` para cada elemento do array.
  - A linha `nullableInts[3].toString()` tenta acessar `toString` em um valor `null`, o que gera uma `NullPointerException`, similar ao comportamento do Java.

- **Diferença Importante:**
  O autor utiliza `arrayOfNulls` explicitamente para demonstrar como Kotlin pode tratar arrays que permitem `null`. Em Java, isso é o comportamento padrão para arrays de objetos, mas em Kotlin, é uma escolha explícita, reforçando a **segurança contra nulos**.

---

### **Por Que o Kotlin É Mais Seguro?**

1. **Segurança contra Nulos:**
   - Kotlin exige que variáveis que podem conter `null` sejam explicitamente declaradas como `nullable` (`String?`).
   - Isso obriga o programador a lidar com `null` usando ferramentas como:
     - **`?.` (Safe Call):** Ignora a operação se o valor for `null`.
     - **`!!` (Not-Null Assertion):** Força a execução, lançando uma NPE se o valor for `null`.

2. **Arrays e Nulos:**
   - Em Kotlin, a criação de arrays que aceitam `null` é explícita (`arrayOfNulls`), enquanto em Java isso ocorre automaticamente com arrays de objetos.

---

### **Resultado da Execução**

#### **Kotlin**
```plaintext
null
null
null
null
null
Exception in thread "main" kotlin.KotlinNullPointerException
```

- A saída mostra que os elementos do array foram inicializados como `null`.
- A tentativa de chamar `toString()` em um valor `null` gera uma `KotlinNullPointerException`.

#### **Java**
```plaintext
Exception in thread "main" java.lang.NullPointerException
```

- A exceção é lançada imediatamente quando o método `toUpperCase()` é chamado em uma referência `null`.

---

### **Como Seria Feito em Java (Sem Kotlin)**

Para implementar algo similar ao código Kotlin no Java, precisaríamos criar manualmente arrays que suportam `null` e adicionar verificações para evitar `NullPointerException`.

#### **Código Java Equivalente**
```java
public class Main {

    public static void main(String[] args) {
        // Criando um array que aceita null
        Integer[] nullableInts = new Integer[5];

        // Iterando sobre o array
        for (Integer i : nullableInts) {
            System.out.println(i); // Todos os elementos são null
        }

        // Tentativa de acessar um valor null
        try {
            System.out.println(nullableInts[3].toString()); // Gera NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NullPointerException ao acessar nullableInts[3]");
        }
    }
}
```

#### **Classe Auxiliar para Métodos**
Se quiséssemos lidar com `null` de forma mais segura em Java, seria necessário criar métodos utilitários:

```java
public class Utils {

    public static void printIfNotNull(String str) {
        if (str != null) {
            System.out.println(str);
        }
    }
}
```

---

### **Comparação Kotlin vs Java**

| Aspecto                     | Kotlin                                         | Java                                         |
|-----------------------------|-----------------------------------------------|---------------------------------------------|
| **Null Safety**              | Suporte nativo com `String?`, `?.`, e `!!`.    | Não há suporte nativo; requer verificações manuais. |
| **Arrays com Nulos**         | `arrayOfNulls<T>(size)` explícito.             | Arrays de objetos permitem `null` por padrão. |
| **Tratamento de Nulos**      | Ferramentas nativas para evitar NPE.           | NPE comum sem verificações manuais.         |

---

### **Conclusão**

O objetivo é destacar:

1. **Problemas com `null`:**
   - Tanto Kotlin quanto Java podem gerar `NullPointerException` ao acessar valores `null` sem verificação.

2. **Diferença de Filosofia:**
   - Kotlin força o desenvolvedor a lidar explicitamente com referências nulas, enquanto em Java isso é responsabilidade do programador.

3. **Escolhas Explícitas em Kotlin:**
   - A criação de arrays que aceitam `null` é intencional (`arrayOfNulls`), enquanto em Java é implícito para arrays de objetos.

Kotlin oferece maior segurança e controle sobre referências nulas, reduzindo erros e tornando o código mais robusto e fácil de manter.

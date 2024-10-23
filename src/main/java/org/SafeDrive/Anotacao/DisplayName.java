package org.SafeDrive.Anotacao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação que define um nome de exibição para um campo.
 * <p>
 * Essa anotação pode ser utilizada para fornecer um nome mais amigável para
 * um campo em uma classe de modelo, facilitando a apresentação em interfaces
 * de usuário ou relatórios.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME) // A anotação estará disponível em tempo de execução.
@Target(ElementType.FIELD) // A anotação pode ser aplicada a campos.
public @interface DisplayName {

        /**
         * O nome de exibição para o campo.
         *
         * @return O nome de exibição.
         */
        String value() default ""; // Valor padrão como string vazia.

        /**
         * Agrupa os nomes de exibição relacionados.
         *
         * @return O grupo ao qual o nome de exibição pertence.
         */
        String group() default ""; // Agrupamento opcional.

        /**
         * Indica se o campo é obrigatório.
         *
         * @return Verdadeiro se o campo for obrigatório, falso caso contrário.
         */
        boolean required() default false; // Indica se o campo é obrigatório.
}

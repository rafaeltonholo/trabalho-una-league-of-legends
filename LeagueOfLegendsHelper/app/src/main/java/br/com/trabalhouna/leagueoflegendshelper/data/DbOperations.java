package br.com.trabalhouna.leagueoflegendshelper.data;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

import br.com.trabalhouna.leagueoflegendshelper.to.BaseTO;

/**
 * Created by Rafael
 *
 * @since 22/06/2015
 */
public interface DbOperations<T extends BaseTO> {
    /**
     * Inseri na database um objeto
     *
     * @param to objeto a ser inserido na db
     * @return 1 se teve sucesso, 0 se não teve
     */
    long insert(T to);

    /**
     * Atualiza um objeto na base de dados
     *
     * @param to objeto que será atualizado
     * @return quantas linhas foram modificadas
     */
    long update(T to);

    /**
     * Deleta da base de dados um objeto
     *
     * @param to objeto que será deletado
     * @return 1 se teve sucesso, 0 se não teve
     */
    long delete(T to);

    /**
     * Lê todos os dados da base de dados de uma determinada tabela
     *
     * @return Lista de objetos
     */
    List<T> read();

    /**
     * Retorna uma lista com objetos a partir de um determinado filtro
     * @param filters filtros utilizados
     * @return Lista de objetos
     */
    List<T> read(DbParam... filters);

    /**
     * Lê um objeto a partir de seu id
     * @param id id do registro
     * @return objeto desejado ou nulo, se não existir
     */
    T read(int id);

    /**
     * Conta quantos registros tem em determinada tabela
     * @return Número de registros
     */
    int count();

    /**
     * Conta quantos registros a partir de um filtro
     * @param filters filtros
     * @return Número de registros
     */
    int count(DbParam... filters);

    /**
     * Binda Cursor a um objeto
     * @param cursor cursor a ser bindado
     * @return objeto
     */
    T bindObject(Cursor cursor);

    /**
     * Seta os valores para inserção no banco de dados
     * @param to objeto que será bindado
     * @return ContentValues
     */
    ContentValues setContentValues(T to);
}

package br.com.trabalhouna.leagueoflegendshelper.data;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by Rafael
 *
 * @since 22/06/2015
 */
public class DbParam {
    private String mColumn;
    private Object mValue;
    private Operation mOperation = Operation.EQUALS;
    private LikeKind mLikeKind = LikeKind.BOTH;
    private DbType mDbType = DbType.TEXT;

    public DbParam() {
        super();
    }

    public DbParam setColumn(@NonNull String column) {
        this.mColumn = column;
        return this;
    }

    /**
     * Seta o valor do parametro. <br>
     * <b>Fique atento com o DbType. DbType default = TEXT</b>.
     *
     * @param value Valor do parametro
     * @return Este objeto para fazer encadeamento de sets.
     */
    public DbParam setValue(@NonNull Object value) {
        this.mValue = value;
        return this;
    }

    public DbParam setOperation(@NonNull Operation operation) {
        this.mOperation = operation;
        return this;
    }

    public DbParam setLikeKind(@NonNull LikeKind likeKind) {
        if (mOperation == Operation.LIKE) {
            this.mLikeKind = likeKind;
        }

        return this;
    }

    public DbParam setDbType(@NonNull DbType dbType) {
        this.mDbType = dbType;

        return this;
    }

    private String getOperator() {
        switch (this.mOperation) {
            case EQUALS:
                return " = ";
            case NOT_EQUALS:
                return " <> ";
            case LESS_THAN:
                return " < ";
            case MORE_THAN:
                return " > ";
            case LESS_OR_EQUALS:
                return " <= ";
            case MORE_OR_EQUALS:
                return " >= ";
            case LIKE:
                return " LIKE ";
            default:
                return null;
        }
    }

    public String getValue() {
        String value = "";

        switch (this.mDbType) {
            case DATE:
            case DATETIME:
                if (this.mValue instanceof Date) value = Long.toString(((Date) this.mValue).getTime());
                else if (this.mValue instanceof Long) value = this.mValue.toString();
                break;
            default:
                value = this.mValue.toString();
        }

        if(mOperation == Operation.LIKE) {
            switch (this.mLikeKind) {
                case END:
                    value += "%";
                    break;
                case START:
                    value = "%" + value;
                    break;
                case BOTH:
                    value = "%" + value + "%";
                    break;
            }
        }

        return value;
    }

    @Override
    public String toString() {
        String operator = getOperator();
        if (operator == null) return "";

        return this.mColumn.concat(" ").concat(operator).concat(" ?");
    }

    public enum Operation {
        EQUALS,
        NOT_EQUALS,
        LESS_THAN,
        MORE_THAN,
        LESS_OR_EQUALS,
        MORE_OR_EQUALS,
        LIKE
    }

    public enum LikeKind {
        END,
        START,
        BOTH
    }


}

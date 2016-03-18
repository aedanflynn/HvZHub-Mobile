package com.hvzhub.app.DB;

import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.DaoConfig;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.Query;
import de.greenrobot.dao.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table MESSAGE.
*/
public class MessageDao extends AbstractDao<Message, Long> {

    public static final String TABLENAME = "MESSAGE";

    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, int.class, "userId", false, "USER_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Message = new Property(3, String.class, "message", false, "MESSAGE");
        public final static Property Timestamp = new Property(4, java.util.Date.class, "timestamp", false, "TIMESTAMP");
        public final static Property MsgId = new Property(5, int.class, "msgId", false, "MSG_ID");
        public final static Property ChatId = new Property(6, long.class, "chatId", false, "CHAT_ID");
    };

    private Query<Message> chat_MessagesQuery;

    public MessageDao(DaoConfig config) {
        super(config);
    }
    
    public MessageDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String sql = "CREATE TABLE " + (ifNotExists? "IF NOT EXISTS ": "") + "'MESSAGE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'USER_ID' INTEGER NOT NULL ," + // 1: userId
                "'NAME' TEXT," + // 2: name
                "'MESSAGE' TEXT," + // 3: message
                "'TIMESTAMP' INTEGER NOT NULL ," + // 4: timestamp
                "'MSG_ID' INTEGER NOT NULL ," + // 5: msgId
                "'CHAT_ID' INTEGER NOT NULL );"; // 6: chatId
        db.execSQL(sql);
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'MESSAGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Message entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getUserId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String message = entity.getMessage();
        if (message != null) {
            stmt.bindString(4, message);
        }
        stmt.bindLong(5, entity.getTimestamp().getTime());
        stmt.bindLong(6, entity.getMsgId());
        stmt.bindLong(7, entity.getChatId());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Message readEntity(Cursor cursor, int offset) {
        Message entity = new Message( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // userId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // message
            new java.util.Date(cursor.getLong(offset + 4)), // timestamp
            cursor.getInt(offset + 5), // msgId
            cursor.getLong(offset + 6) // chatId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Message entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setUserId(cursor.getInt(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setMessage(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setTimestamp(new java.util.Date(cursor.getLong(offset + 4)));
        entity.setMsgId(cursor.getInt(offset + 5));
        entity.setChatId(cursor.getLong(offset + 6));
     }
    
    @Override
    protected Long updateKeyAfterInsert(Message entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Message entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "messages" to-many relationship of Chat. */
    public synchronized List<Message> _queryChat_Messages(long chatId) {
        if (chat_MessagesQuery == null) {
            QueryBuilder<Message> queryBuilder = queryBuilder();
            queryBuilder.where(Properties.ChatId.eq(chatId));
            queryBuilder.orderRaw("TIMESTAMP DESC");
            chat_MessagesQuery = queryBuilder.build();
        } else {
            chat_MessagesQuery.setParameter(0, chatId);
        }
        return chat_MessagesQuery.list();
    }

}

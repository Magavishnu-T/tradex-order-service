DO $$ 

DECLARE
    schema_name TEXT := 'tradex_management';
    table_name  TEXT := 'orders_table';
BEGIN
    -- Create Orders Table
    IF NOT EXISTS (
        SELECT 1
        FROM information_schema.tables
        WHERE information_schema.tables.table_schema = schema_name
          AND information_schema.tables.table_name = table_name
    ) THEN
        EXECUTE format(
            'CREATE TABLE %I.%I (
                order_id BIGSERIAL PRIMARY KEY,
                symbol VARCHAR(50),
                quantity INTEGER,
                side VARCHAR(10),
                order_type VARCHAR(20),
                price NUMERIC(19,4),
                status VARCHAR(20),
                event_id VARCHAR(50),
                created_on TIMESTAMPTZ,
                created_by VARCHAR(50),
                updated_on TIMESTAMPTZ,
                updated_by VARCHAR(50)
            )', schema_name, table_name
        );
        RAISE NOTICE 'Table "%"."%" created successfully.', schema_name, table_name;
    ELSE
        RAISE NOTICE 'Table "%"."%" already exists. Skipping creation.', schema_name, table_name;
    END IF;
END $$;
# Deploying Elixir - Advanved Topics by Miguel Coba
https://blog.miguelcoba.com/deploying-elixir-advanced-topics-ebook

## Notes

#### Create a sample application
```sh
mix phx.new neptune --install
cd neptune

mix ecto.create
mix phx.server
```

#### Production environment
Create prod database
```sh
psql -U postgres
```

```sql
CREATE DATABASE neptune_prod;
```

Show all databases
```sql
SELECT datname FROM pg_database;
```

Exit the CLI
```sql
\q
```

#### Run the application using Elixir Release
Release Docker configuration
```sh
mix phx.gen.release --docker --ecto
```
Create a release
```sh
MIX_ENV=prod mix release
```

Environment variables
```sh
export SECRET_KEY_BASE=$(mix phx.gen.secret)
export PORT=4000
export PHX_SERVER=true
export DATABASE_URL=ecto://postgres:postgres@localhost/neptune_prod
```

Start the server using the release
```sh
_build/prod/rel/neptune/bin/neptune start
```

#### Run the applications in Docker
Build
```sh
docker build -t neptune .
```

Run
```sh
docker run --rm -d -p $PORT:$PORT -e PORT -e SECRET_KEY_BASE -e DATABASE_URL --name neptune neptune
```

Stop
```sh
docker stop neptune
```
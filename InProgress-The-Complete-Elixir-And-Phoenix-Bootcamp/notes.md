# The Complete Elixir and Phoenix Bootcamp
## Generating a new project
```bash
mix new my_project_name
```

## Compiling the code and running the Interactive Elixir Shell
```bash
iex -S mix
```

## Recompile in the IEX
```iex
recompile
```
## Installing ex-doc documentation generation package
- Add package name and version as a tuple in the mix.exs file
- Run in the bash / command prompt
```bash
mix deps.get
```
- Add `@moduledoc` and `@doc`
- Run in the bash / command prompt
```bash
mix docs
```

## Running tests
```iex
mix test
```

## Issues with EGD
In case of EGD not being seen by the compiler:
- Check the dependencies
- Get the dependencies
- If all fails, run line below
```
mix local.rebar --force
```

## Phoenix 1.2
### Installation
```bash
mix archive.install https://github.com/phoenixframework/archives/raw/master/phoenix_new-1.2.5.ez
```

### Create a new project
```bash
mix phoenix.new my_project_name
```
### Configure connection with Ecto
- Change user name, password and port and run:
```bash
mix ecto.create
```

### Running a new Phoenix server
```bash
mix phoenix.server
```

### Generating migration file
```bash
mix ecto.gen.migration migration_name
```

### Execute migration
```bash
mix ecto.migrate
```

### Import, alias, use
- ```import``` -> take all the functions out of this module and give them to this other module
- ```alias``` -> short the name
- ```use``` -> fancy setup

###
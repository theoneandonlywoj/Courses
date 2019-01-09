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
# Project Template

This is a template for a project that uses the following setup:

## Backend

The backend is a Java Spring Boot application with Spring Security and JWT authentication. It uses an H2 database for development.

## Frontend

The frontend is a React application written in TypeScript. It uses TailwindCSS for styling and React Router for routing.

## Git Alias

Git Aliases are used for creating branches and switching between branches based on the ticket they're associated with.

In order to run custom aliases run the following command

```bash
git config include.path ../alias/.gitalias
```

This unlocks the following aliases

| Command                 | Description                                                                                                                                 |
| ----------------------- | ------------------------------------------------------------------------------------------------------------------------------------------- |
| `git ticket XX`         | Switch to the branch for ticket `XX`, if multiple branches match a ticket number, you are asked to select which branch you want to checkout |
| `git nb XX description` | Create a new branch called `WAXX-description` and switch to it          

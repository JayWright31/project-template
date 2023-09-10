export default {
  "*.*": "prettier --write",
  "ui/**/*": "prettier --write",
  "api/**/!(*.cmd)": "prettier --write",
  "api/**/*": () => "npm run lint-api",
  "ui/**/*.{ts,tsx}": "npm run lint-ui",
};

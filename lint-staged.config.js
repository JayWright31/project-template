export default {
  // "*.*": "prettier --write",
  "ui/**/*": "prettier --write",
  "api/**/!(*.cmd)": "google-java-format -i",
  "api/**/*": () => "npm run lint-api",
  "ui/**/*.{ts,tsx}": "npm run lint-ui",
};

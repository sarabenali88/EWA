import { createI18n } from 'vue-i18n'

  const messages = {
    nl: require('./locales/nl.json'),
    en: require('./locales/en.json'),
    fr: require('./locales/fr.json')
  }

export default createI18n({
  locale: 'nl',
  fallbackLocale:  'en',
  messages: messages
})

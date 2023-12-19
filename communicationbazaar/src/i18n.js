/**
 * set up to be able to use the vue-18in for translations
 *
 * @author Sara Benali
 */
import { createI18n } from 'vue-i18n'

/**
 * The languages that are used for translations with their own json files
 *
 * @author Sara Benali
 */
  const messages = {
    nl: require('./locales/nl.json'),
    en: require('./locales/en.json'),
    fr: require('./locales/fr.json')
  }

/**
 * standard language is dutch and fallback is english and sends the json files with it
 *
 * @author Sara Benali
 */
export default createI18n({
  locale: 'nl',
  fallbackLocale:  'en',
  messages: messages
})

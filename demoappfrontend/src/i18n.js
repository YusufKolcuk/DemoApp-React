import i18n from 'i18next';
import {initReactI18next} from 'react-i18next';
import i18n_de from './language/i18n_de.json'
import i18n_tr from './language/i18n_tr.json'
import i18n_en from './language/i18n_en.json'

i18n.use(initReactI18next).init({
    resources: {
        en:{ translations: i18n_en },
        tr:{ translations: i18n_tr },
        de:{ translations: i18n_de }
    },
    fallbackLng: 'de',
    ns: ['translations'],
    defaultNS: 'translations',
    keySeparator:false,
    interpolation:{
        escapeValue:false,
        formatSeparator: ','
    },
    react: {
        wait:true
    }
});

export default i18n;
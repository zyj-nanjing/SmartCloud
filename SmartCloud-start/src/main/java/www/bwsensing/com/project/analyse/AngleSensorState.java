package www.bwsensing.com.project.analyse;

import com.alibaba.cola.exception.BizException;
import org.springframework.util.Assert;
import www.bwsensing.com.common.utills.SensorUtils;
import www.bwsensing.com.project.analyse.domain.SensorData;
import java.util.ArrayList;
import java.util.List;

/**
 * 倾角传感
 * @author macos-zyj
 */
public class AngleSensorState implements SensorBaseState{
    private static final Integer CONTAIN_ACCELERATE = 10;
    private static final Integer NOT_CONTAIN_ACCELERATE = 5 ;
    private static final Integer FUNCTION_CODE_SIZE = 8;
    private static final Integer BIT_LENGTH = 2;
    private static final Integer FUNCTION_MODEL_LENGTH = 3;
    private static final int[]  HEX_CODE_FORMAT = {1,1,1,16,2};


    @Override
    public SensorData analyseRawData(String raw) {
        SensorData analyseData = new SensorData();
        analyseData.setRaw(raw);
        if (checkFormat(raw)){
            analyseData.setHexDecode(true);
        }
        analyseRawData(analyseData);
        return analyseData;
    }

    private Boolean checkFormat(String raw){
        String[] codeArray = raw.split(",");
        return codeArray.length <= 1;
    }

    private void analyseRawData(SensorData analyseData){
        if (analyseData.isHexDecode()){
            hexAnalyse(analyseData);
        } else{
            asciiAnalyse(analyseData);
        }
    }

    private void hexAnalyse(SensorData analyseData){
        String toNoNull = analyseData.getRaw().replaceAll(" ","");
        List<String> codeSplitArray = codeSplit(toNoNull);
        analyseData.setSn(SensorUtils.hexSnAnalyse(codeSplitArray.get(0)));
        initFunctionCode(codeSplitArray.get(3),analyseData);
    }

    private List<String> codeSplit(String hexCode){
        List<String> codeSplitArray = new ArrayList<>(6);
        try {
            int index = 0;
            for (int i : HEX_CODE_FORMAT) {
                int endIndex = index + 2 * i;
                String currentBitCode = hexCode.substring(index, endIndex);
                codeSplitArray.add(currentBitCode);
                index = endIndex;
            }
        }  catch (StringIndexOutOfBoundsException exception){
            throw new BizException("HEX_FORMAT_NOT_TRUE","接收数据格式不正确");
        }
        return codeSplitArray;
    }

    private void initFunctionCode(String functionHex,SensorData analyseData){
        List<String> functionCodeArray = new ArrayList<>(9);
        for (int i=0; i< FUNCTION_CODE_SIZE; i++){
            String codeSplits = functionHex.substring(i*4,(i+1)*4);
            functionCodeArray.add(codeSplits);
        }
        analyseData.setXAngle(toAngleFormat(functionCodeArray.get(0),true));
        analyseData.setYAngle(toAngleFormat(functionCodeArray.get(1),true));
        analyseData.setZAngle(toAngleFormat(functionCodeArray.get(2),true));
        analyseData.setXAccelerate(toAccelerateFormat(functionCodeArray.get(3),true).floatValue());
        analyseData.setYAccelerate(toAccelerateFormat(functionCodeArray.get(4),true).floatValue());
        analyseData.setZAccelerate(toAccelerateFormat(functionCodeArray.get(5),true).floatValue());
        analyseData.setTemperature(toTemperatureFormat(functionCodeArray.get(6),true));
        analyseData.setElectricity(electricityAnalyse(functionCodeArray.get(7),true).floatValue());
    }

    private Double electricityAnalyse(String analyseString, boolean isHex){
        if(isHex){
            return Integer.parseInt(analyseString,16)*1.0;
        } else{
            return Double.parseDouble(analyseString);
        }
    }

    private void asciiAnalyse(SensorData analyseData){
        String toNoNull = analyseData.getRaw().replaceAll(" ","");
        String[] dataArray = toNoNull.split(",");
        Assert.isTrue(dataArray.length == CONTAIN_ACCELERATE ||
                dataArray.length == NOT_CONTAIN_ACCELERATE,"ASCII format not true!");
        analyseData.setSn(dataArray[0].replace("+",""));
        if (dataArray.length == NOT_CONTAIN_ACCELERATE){
            asciiAngleAnalyse(dataArray, false,analyseData);
            sensorStatesAnalyse(dataArray,3,false,analyseData);
        } else {
            asciiAngleAnalyse(dataArray, true,analyseData);
            asciiAccelerateAnalyse(dataArray, analyseData);
            sensorStatesAnalyse(dataArray,7,true,analyseData);
        }
    }

    private void asciiAngleAnalyse(String[] dataArray, boolean isContainAcc, SensorData analyseData){
        analyseData.setXAngle(toAngleFormat(dataArray[1],false));
        analyseData.setYAngle (toAngleFormat(dataArray[1 +1],false));
        if(isContainAcc){
            analyseData.setZAngle(toAngleFormat(dataArray[1 +2],false));
        }
    }

    private void asciiAccelerateAnalyse(String[] dataArray, SensorData analyseData){
        analyseData.setXAccelerate(toAccelerateFormat(dataArray[4],false).floatValue());
        analyseData.setYAccelerate(toAccelerateFormat(dataArray[5],false).floatValue());
        analyseData.setZAccelerate(toAccelerateFormat(dataArray[6],false).floatValue());
    }

    private void  sensorStatesAnalyse(String[] dataArray,int start,boolean isContainAcc, SensorData analyseData){
        analyseData.setTemperature(toTemperatureFormat(dataArray[start],false));
        analyseData.setElectricity(electricityAnalyse(dataArray[start+1],false).floatValue());
        if(isContainAcc){
            analyseData.setPhoneNumber(dataArray[start+2]);
        }
    }

    private Double toAccelerateFormat(String analyseString,boolean isHex){
        double dataDouble ;
        if (isHex){
            dataDouble = (Integer.parseInt(analyseString,16)-20000)*1.0/10000;
        }else{
            dataDouble = asciiToDouble(analyseString);
        }
        return dataDouble;
    }

    private Float toAngleFormat(String analyseString,boolean isHex){
        float dataDouble ;
        if (isHex){
            dataDouble = (float) ((Integer.parseInt(analyseString,16)-20000)*1.0/100);
        }else{
            dataDouble = asciiToDouble(analyseString);
        }
        return dataDouble;
    }

    private Float toTemperatureFormat(String analyseString,boolean isHex){
        float dataDouble ;
        if (isHex){
            dataDouble = (float) ((Integer.parseInt(analyseString,16)-20000)*1.0/100);
        }else{
            dataDouble = asciiToDouble(analyseString);
        }
        return dataDouble;
    }

    private Float asciiToDouble(String ascii){
        return Float.parseFloat(ascii.replace("+",""));
    }
}
